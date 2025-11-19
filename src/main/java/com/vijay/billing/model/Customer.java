package com.vijay.billing.model;

public record Customer(
        String id,
        String name,
        Plan plan,
        double monthlyUsageHours,
        boolean nonProfit
) { }
