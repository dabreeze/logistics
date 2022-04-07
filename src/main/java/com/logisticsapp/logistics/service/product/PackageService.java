package com.logisticsapp.logistics.service.product;

import com.logisticsapp.logistics.data.dto.AddPackageRequest;
import com.logisticsapp.logistics.data.dto.AddPackageResponse;
import com.logisticsapp.logistics.data.models.PackageModel;
import com.logisticsapp.logistics.web.exceptions.BusinessLogicException;
import com.logisticsapp.logistics.web.exceptions.PackageDoesNotExistException;

import java.util.List;

public interface PackageService {
    AddPackageResponse createPackage(AddPackageRequest packageDto) throws BusinessLogicException;
    List<PackageModel>getAllPackages();
    AddPackageResponse findPackageById(Long packageId)throws PackageDoesNotExistException;
    void deletePackage(Long id)throws PackageDoesNotExistException;


}
