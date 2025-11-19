package com.vijay.billing.model;

public record Invoice(
        String customerId,
        String customerName,
        String planName,
        double usageHours,
        double baseAmount,
        double discountAmount,
        double finalAmount
) { }
