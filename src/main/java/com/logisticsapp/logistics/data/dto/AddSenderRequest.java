package com.logisticsapp.logistics.data.dto;

import com.logisticsapp.logistics.data.models.Sex;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AddSenderRequest {
//    private Long PackageId;
    private String firstName;
    private String lastName;
    private String shippingAddress;
    private String phone;
    private ArrayList packageName;
    private Double weight;
    private Sex gender;
    private String password;
    private String senderEmail;


}
