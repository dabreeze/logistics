package com.logisticsapp.logistics.utils;

import com.logisticsapp.logistics.data.dto.AddSenderRequest;
import com.logisticsapp.logistics.data.dto.AddSenderResponse;
import com.logisticsapp.logistics.data.models.Sender;

import java.time.LocalDateTime;

public class ModelMapper {
    public static Sender map(AddSenderRequest addSenderRequest){
        Sender aSender = new Sender();
        aSender.setFirstName(addSenderRequest.getFirstName());
        aSender.setLastName(addSenderRequest.getLastName());
        aSender.setSenderEmail(addSenderRequest.getSenderEmail());
        aSender.setSenderAddress(addSenderRequest.getShippingAddress());
        aSender.setDateCreated(LocalDateTime.now());
        aSender.setSenderPhone(addSenderRequest.getPhone());
        aSender.setGender(addSenderRequest.getGender());
        aSender.setPackageModels(addSenderRequest.getPackageName());
        aSender.setPassword(addSenderRequest.getPassword());

        return aSender;
    }

    public static AddSenderResponse map(Sender aSender) {
        AddSenderResponse responseDto = new AddSenderResponse();
        responseDto.setFirstName(aSender.getFirstName());
        responseDto.setSenderEmail(aSender.getSenderEmail());
        responseDto.setLastName(aSender.getLastName());
        responseDto.setPhone(aSender.getSenderPhone());

        return responseDto;
    }

//    public static PackageModel map(AddPackageRequest addPackageRequest){
//        PackageModel aPackage = new PackageModel();
//        aPackage.setPackageName(addPackageRequest.getPackageName());
//        aPackage.setPackageDescription(addPackageRequest.getDescription());
//        aPackage.setShippingAddress(addPackageRequest.getShippingAddress());
//        aPackage.setReceiverPhoneNumber(addPackageRequest.getReceiverPhone());
//        aPackage.setWeight(addPackageRequest.getPackageWeight());
//
//
//    }


}
