package com.vijay.billing.model;

public sealed interface Plan permits FreePlan, StandardPlan, EnterprisePlan {
    String name();
}
