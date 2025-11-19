package com.vijay.billing.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InvoiceRequest(
        @NotBlank String customerId,
        @NotBlank String customerName,
        @NotBlank String planType,
        @Min(0) double usageHours,
        @NotNull Boolean nonProfit
) { }
