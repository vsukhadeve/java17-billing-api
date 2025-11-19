package com.vijay.billing.model;

public final class FreePlan implements Plan {
    @Override
    public String name() {
        return "FREE";
    }
}
