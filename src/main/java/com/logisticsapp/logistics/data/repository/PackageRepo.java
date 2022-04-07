package com.logisticsapp.logistics.data.repository;

import com.logisticsapp.logistics.data.models.PackageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PackageRepo extends JpaRepository<PackageModel, Long> {
    Optional<PackageModel> findByPackageName(String name);
}
