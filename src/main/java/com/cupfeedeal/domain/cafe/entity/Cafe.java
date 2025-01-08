package com.cupfeedeal.domain.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressMap;

    @Column(nullable = false)
    private String operationTime;

    @Column(nullable = false)
    private String subscriptionPrice;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String snsAddress;

    @Column
    private String menuBoard;

    @Column
    private Boolean isRecommended = false;

    @Column
    private Boolean isNewOpen = false;

    @PrePersist
    public void prePersist() {
        if (isRecommended == null) {
            isRecommended = false;
        } if (isNewOpen == null) {
            isNewOpen = false;
        }
    }

    @Builder
    public Cafe(String name, String address, String addressMap, String operationTime, String subscriptionPrice, String description, String phoneNumber, String snsAddress, String menuBoard, Boolean isRecommended, Boolean isNewOpen) {
        this.name = name;
        this.address = address;
        this.addressMap = addressMap;
        this.operationTime = operationTime;
        this.subscriptionPrice = subscriptionPrice;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.snsAddress = snsAddress;
        this.menuBoard = menuBoard;
        this.isRecommended = isRecommended;
        this.isNewOpen = isNewOpen;
    }
}
