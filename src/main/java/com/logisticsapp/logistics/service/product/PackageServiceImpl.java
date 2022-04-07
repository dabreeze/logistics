package com.logisticsapp.logistics.service.product;

import com.logisticsapp.logistics.data.dto.AddPackageRequest;
import com.logisticsapp.logistics.data.dto.AddPackageResponse;
import com.logisticsapp.logistics.data.models.PackageModel;
import com.logisticsapp.logistics.data.models.Sender;
import com.logisticsapp.logistics.data.repository.PackageRepo;
import com.logisticsapp.logistics.web.exceptions.BusinessLogicException;
import com.logisticsapp.logistics.web.exceptions.PackageDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PackageServiceImpl implements PackageService{


    private final PackageRepo packageRepo;
    @Autowired
    public  PackageServiceImpl(PackageRepo packageRepo){this.packageRepo = packageRepo;}

    AddPackageRequest addPackageRequest;
    private PackageModel aPackage;

    @Override
    public List<PackageModel> getAllPackages() {
        return packageRepo.findAll();
    }

    @Override
    public AddPackageResponse findPackageById(Long packageId) throws PackageDoesNotExistException {

        if(packageId == null) {
            throw new PackageDoesNotExistException("The package id :" + packageId + " is empty please enter a valid id");
        }
        PackageModel packageModel = packageRepo.findById(packageId).orElse(null);

//        AddPackageResponse optionalPackageModel = packageRepo.findById(packageId).orElse(null);
        if (packageModel == null)
            throw new PackageDoesNotExistException("The package with this id :"+ packageId+" does not exist");

        return buildPackageResponse(packageModel);

    }
    private AddPackageResponse buildPackageResponse(PackageModel packageModel){
        return AddPackageResponse.builder()
                .packageName(packageModel.getPackageName())
                .packageWeight(packageModel.getWeight())
                .receiverName(packageModel.getReceiverName())
                .receiverPhone(packageModel.getReceiverPhoneNumber())
                .build();
    }


    @Override
    public void deletePackage(Long id) throws PackageDoesNotExistException {
        if (packageRepo.findById(id).isEmpty() )
            throw new PackageDoesNotExistException("There is no package here!");
        packageRepo.deleteAll();
    }

    @Override
    public AddPackageResponse createPackage(AddPackageRequest addPackageRequest) throws BusinessLogicException {
        validateDtoContent(addPackageRequest);
        aPackage = new PackageModel();

        PackageModel mappedPackage = mapPackageDtoToPackage(addPackageRequest, aPackage);
        packageRepo.save(mappedPackage);
        return buildResponse(mappedPackage);
    }

    private PackageModel mapPackageDtoToPackage(AddPackageRequest addPackageRequest, PackageModel aPackage) {

        aPackage.setPackageName(addPackageRequest.getPackageName());
        aPackage.setPackageDescription(addPackageRequest.getDescription());
        aPackage.setReceiverPhoneNumber(addPackageRequest.getReceiverPhone());
        aPackage.setShippingAddress(addPackageRequest.getShippingAddress());
        aPackage.setWeight(addPackageRequest.getPackageWeight());
        aPackage.setReceiverName(addPackageRequest.getReceiverName());
        return aPackage;
    }
    private void validateDtoContent(AddPackageRequest addPackageRequest) throws BusinessLogicException {
//        boolean receiverNumberIsEmpty = addPackageRequest.getReceiverPhone()==null;
//        boolean receiverNameIsEmpty = addPackageRequest.getReceiverName()==null;
//        boolean packageNameIsEmpty = addPackageRequest.getPackageName()==null;
//        boolean shippingAddressIsEmpty = addPackageRequest.getShippingAddress()==null;
//
//        if (receiverNameIsEmpty || receiverNumberIsEmpty||packageNameIsEmpty||shippingAddressIsEmpty)
//            throw new BusinessLogicException("Package details cannot be empty");

        if(addPackageRequest == null){
            throw new BusinessLogicException("Package details cannot be empty");
        }
    }

    private AddPackageResponse buildResponse(PackageModel aPackage){
        return AddPackageResponse.builder()
                .packageName(aPackage.getPackageName())
                .receiverName(aPackage.getReceiverName())
                .receiverPhone(aPackage.getReceiverPhoneNumber())
                .packageWeight(aPackage.getWeight()).build();
    }
}
