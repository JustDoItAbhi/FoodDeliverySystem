package com.CustomerService.servcies;

import com.CustomerService.dtos.CustomerResponseDto;
import com.CustomerService.dtos.CustomerSignUp;
import com.CustomerService.exceptions.CustomerAlreadyExsits;
import com.CustomerService.exceptions.CustomerNotFoundException;
import com.CustomerService.kafka.KafkaConstrains;
import com.CustomerService.kafka_email_service.EmailDto;
import com.CustomerService.kafka_email_service.JavaEmailService;
import com.CustomerService.mapper.CustomerMappers;
import com.CustomerService.models.Address;
import com.CustomerService.models.Customers;
import com.CustomerService.repositories.AddressRepository;
import com.CustomerService.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private KafkaTemplate<String, EmailDto>kafkaTemplate;
    @Autowired
    private JavaEmailService emailService;

    @Override
    public CustomerResponseDto signupCustomer(CustomerSignUp signUp) {
        Optional<Customers>savedCustomer=customerRepository.findByEmail(signUp.getEmail());
        if(savedCustomer.isPresent()){
            throw new CustomerAlreadyExsits("CUSTOMER ALREADY EXISTS PLEASE LOGIN "+ signUp.getEmail());
        }
        List<CustomerResponseDto>customersList=new ArrayList<>();
        Customers customers=new Customers();
        customers.setCustomerName(signUp.getCustomerName());
        customers.setEmail(signUp.getEmail());
        customers.setPassword(passwordEncoder.encode(signUp.getPassword()));
        Address address=new Address();
        address.setCity(signUp.getAddress().getCity());
        address.setBuildingNumber(signUp.getAddress().getBuildingNumber());
        address.setStreet(signUp.getAddress().getStreet());
        address.setCountry(signUp.getAddress().getCountry());
        address.setPinCode(signUp.getAddress().getPinCode());
        addressRepository.save(address);
        customers.setAddress(address);
        customerRepository.save(customers);
        EmailDto dto = new EmailDto();
        dto.setEmailTo(customers.getEmail());
        dto.setEmailSubject("Welcome to Our Resturant!");
        dto.setEmailMessage("Hi " + customers.getCustomerName() + ", welcome aboard!:::::"+ customers.getCreatedAt());
        kafkaTemplate.send(KafkaConstrains.kafkaTopic, dto);

        return CustomerMappers.fromEntity(customers);
    }

    @Override
    public CustomerResponseDto login(String email, String password) {
        Optional<Customers>savedCustomer=customerRepository.findByEmail(email);
        if(savedCustomer.isEmpty()){
            throw new CustomerNotFoundException("CUSTOMER NOT EXISTS PLEASE SIGN UP "+ email);
        }
        Optional<Customers>saved=customerRepository.findByEmail(email);
        if(!passwordEncoder.matches(password,savedCustomer.get().getPassword())){
            throw new CustomerNotFoundException("PLEASE ENTER VALID PASSWORD "+ password);
        }

        EmailDto dto=new EmailDto();
        dto.setEmailTo(email);
        dto.setEmailSubject("THANKS FOR LOGIN");
        String message= "YOU CAN CHOOSE YOUR CHOICE OF FOOD FROM OUR RESTURANATS";

        dto.setEmailMessage(message);
        kafkaTemplate.send(KafkaConstrains.kafkaTopic, dto);
        return CustomerMappers.fromEntity(savedCustomer.get());
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customers>customers=customerRepository.findAll();
        List<CustomerResponseDto>responseDtos=new ArrayList<>();
        for(Customers customers1:customers){
            responseDtos.add(CustomerMappers.fromEntity(customers1));
        }
        return responseDtos;
    }

    @Override
    public boolean deleteCustomer(long id) {
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public CustomerResponseDto getByEmail(String email) {
        Optional<Customers>savedCustomer=customerRepository.findByEmail(email);
        if(savedCustomer.isEmpty()){
            throw new CustomerNotFoundException("THIS EMAIL NOT EXISTS PLEASE SIGN UP "+ email);
        }

        return CustomerMappers.fromEntity(savedCustomer.get());
    }
}
