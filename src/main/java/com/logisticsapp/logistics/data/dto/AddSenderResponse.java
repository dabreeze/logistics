package com.logisticsapp.logistics.data.dto;

import lombok.Data;

@Data
public class AddSenderResponse {
    private String firstName;
    private String lastName;
    private String senderEmail;
    private String phone;

}
