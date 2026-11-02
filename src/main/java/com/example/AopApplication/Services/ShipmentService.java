package com.example.AopApplication.Services;

public interface ShipmentService {

    String orderPackage(Long orderId);

    String trackPackage(Long orderId);
}
