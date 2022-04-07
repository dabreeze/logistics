package com.logisticsapp.logistics.web.controllers;

import com.logisticsapp.logistics.data.dto.AddPackageRequest;
import com.logisticsapp.logistics.data.dto.AddPackageResponse;
import com.logisticsapp.logistics.service.product.PackageService;
import com.logisticsapp.logistics.web.exceptions.BusinessLogicException;
import com.logisticsapp.logistics.web.exceptions.PackageDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/logistics")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @PostMapping("/createPackage")
    public ResponseEntity<?> createPackage(@RequestBody AddPackageRequest addPackageRequest){
        AddPackageResponse response;
        try{
            response = packageService.createPackage(addPackageRequest);
            return ResponseEntity.ok().body(response);
        } catch (BusinessLogicException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @RequestMapping(value = "/logistics/{packageId}")
    public ResponseEntity<?> findPackageById(@PathVariable("packageId") Long id){
        try{
            AddPackageResponse addPackageResponse = packageService.findPackageById(id);
            return ResponseEntity.status(HttpStatus.OK).body(addPackageResponse);
        } catch (PackageDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


//    public
}
