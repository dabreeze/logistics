package com.logisticsapp.logistics.service.product;

import com.logisticsapp.logistics.data.dto.AddPackageRequest;
import com.logisticsapp.logistics.data.models.PackageModel;
import com.logisticsapp.logistics.data.repository.PackageRepo;
import com.logisticsapp.logistics.web.exceptions.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = "/static/insert.sql")
class PackageServiceImplTest {
    @Autowired
    PackageRepo packageRepo;

    PackageModel apackage;


//    PackageModel aPackage;

    AddPackageRequest addPackageRequest;

    PackageService packageService;


    @BeforeEach
    void setUp() {
        apackage = new PackageModel();
    }

    @Test
    void createPackage() throws BusinessLogicException {

        apackage.setPackageName("Wooden chair");
        apackage.setWeight(10.9);
        apackage.setPackageDescription("gold colored chairs");
        apackage.setReceiverPhoneNumber("0903334234");
        apackage.setReceiverName("Grace");
        apackage.setShippingAddress("jibowu bus stop");
        packageRepo.save(apackage);
        log.info("this is the newly created package left:: -->{}",apackage);
        assertThat(apackage.getPackageId()).isNotNull();
        assertThat(apackage.getPackageName()).isEqualTo("Wooden chair");
    }

    @Test
    void getAllPackages() {
        List<PackageModel> apackage = packageRepo.findAll();
        log.info("this is all the package left:: -->{}",apackage);
        assertThat(apackage.size()).isEqualTo(2);
    }

    @Test
    void findPackageById() {
        apackage = packageRepo.findById(1L).orElse(null);
        log.info("this is the with ID package left:: -->{}",apackage);
        assertThat(apackage.getPackageId()).isEqualTo(1);
        assertThat(apackage.getPackageId()).isNotNull();
        assertThat(apackage.getPackageName()).isEqualTo("Mobile phone");

    }

    @Test
    void deletePackage() {
        packageRepo.deleteAll();
        assertThat(packageRepo.findAll().size()).isZero();
    }



}