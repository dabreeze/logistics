package com.logisticsapp.logistics.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class PackageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long packageId;

    private String packageName;

    @Column(length = 500)
    private String shippingAddress;


    private String receiverPhoneNumber;

    private String receiverName;

    @Column(length = 500)
    private String packageDescription;

    @CreationTimestamp
    private LocalDateTime dateCreated = LocalDateTime.now();

    private Double weight;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Sender senderId;
}
