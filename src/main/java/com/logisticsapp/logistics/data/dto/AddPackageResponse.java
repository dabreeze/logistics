package com.logisticsapp.logistics.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddPackageResponse {
    private Integer trackingNumber;
    private String packageName;
    private String receiverName;
    private String receiverPhone;
    private double packageWeight;
}
