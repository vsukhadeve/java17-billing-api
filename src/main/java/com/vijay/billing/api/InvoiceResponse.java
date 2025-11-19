package com.vijay.billing.api;

public record InvoiceResponse(
        String customerId,
        String customerName,
        String plan,
        double usageHours,
        double baseAmount,
        double discountAmount,
        double finalAmount
) { }
