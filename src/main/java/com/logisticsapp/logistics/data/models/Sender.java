package com.logisticsapp.logistics.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Sender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long senderId;

    private String firstName;

    private String lastName;

    @Column(unique = true,nullable = false)
    private String senderEmail;

    @Column(length = 500)
    private String senderAddress;

    private String senderPhone;

    @Enumerated(EnumType.STRING)
    private Sex gender;

    @Column(nullable = false)
    private String password;


    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PackageModel> packageModels;

    @CreationTimestamp
    private LocalDateTime dateCreated;
}
