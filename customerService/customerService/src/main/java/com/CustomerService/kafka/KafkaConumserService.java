package com.CustomerService.kafka;

import com.CustomerService.kafka_email_service.EmailDto;
import com.CustomerService.kafka_email_service.JavaEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConumserService {
    @Autowired
    private JavaEmailService emailService;
@KafkaListener(
        topics = KafkaConstrains.kafkaTopic,
        groupId = KafkaConstrains.KAFKA_GROUP_ID,
        containerFactory = "concurrentKafkaListenerContainerFactory"
)
    public void consumeMessage(EmailDto dto) {
        System.out.println("📥 MESSAGE RECEIVED for: " + dto.getEmailTo());
        emailService.javaMailServices(dto);
    }


}
