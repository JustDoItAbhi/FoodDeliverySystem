package com.CustomerService.kafka_email_service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {
    private String emailFrom;
    private String emailTo;
    private String emailSubject;
    private String emailMessage;

    public EmailDto() {
    }
}
