package com.vijay.billing.service;

import com.vijay.billing.model.Customer;
import com.vijay.billing.model.EnterprisePlan;
import com.vijay.billing.model.FreePlan;
import com.vijay.billing.model.Invoice;
import com.vijay.billing.model.Plan;
import com.vijay.billing.model.StandardPlan;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    public Invoice generateInvoice(Customer customer) {
        double base = calculateBaseAmount(customer);
        double discount = calculateDiscount(customer, base);
        double finalAmount = base - discount;

        return new Invoice(
                customer.id(),
                customer.name(),
                customer.plan().name(),
                customer.monthlyUsageHours(),
                base,
                discount,
                finalAmount
        );
    }

    private double calculateBaseAmount(Customer customer) {
        Plan plan = customer.plan();
        double usage = customer.monthlyUsageHours();

        if (plan instanceof FreePlan) {
            return 0.0;
        } else if (plan instanceof StandardPlan) {
            double extra = Math.max(0, usage - 10);
            return 20 + extra * 2.0;
        } else if (plan instanceof EnterprisePlan) {
            double extra = Math.max(0, usage - 50);
            return 200 + extra * 1.0;
        } else {
            throw new IllegalStateException("Unknown plan: " + plan);
        }
    }

    private double calculateDiscount(Customer customer, double base) {
        double nonProfitDiscount = customer.nonProfit() ? 0.15 : 0.0;

        double planLevelDiscount = switch (customer.plan().name()) {
            case "STANDARD" -> 0.05;
            case "ENTERPRISE" -> 0.10;
            default -> 0.0;
        };

        double effectiveDiscount = nonProfitDiscount + planLevelDiscount;
        effectiveDiscount = Math.min(effectiveDiscount, 0.25);

        return base * effectiveDiscount;
    }

    public Plan fromPlanType(String planType) {
        return switch (planType.toUpperCase()) {
            case "FREE" -> new FreePlan();
            case "STANDARD" -> new StandardPlan();
            case "ENTERPRISE" -> new EnterprisePlan();
            default -> throw new IllegalArgumentException("Unsupported plan type: " + planType);
        };
    }
}
