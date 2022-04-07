package com.logisticsapp.logistics.data.dto;

import lombok.Data;

@Data
public class AddPackageRequest {
    private String packageName;
    private String receiverName;
    private String senderEmail;
    private String shippingAddress;
    private String receiverPhone;
    private String description;
    private Double packageWeight;

}
