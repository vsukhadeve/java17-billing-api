package com.vijay.billing.api;

import com.vijay.billing.model.Customer;
import com.vijay.billing.model.Invoice;
import com.vijay.billing.model.Plan;
import com.vijay.billing.service.BillingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
@Tag(name = "Billing", description = "Subscription billing operations")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping
    @Operation(summary = "Generate an invoice for a customer")
    public ResponseEntity<InvoiceResponse> createInvoice(@Valid @RequestBody InvoiceRequest request) {
        Plan plan = billingService.fromPlanType(request.planType());

        Customer customer = new Customer(
                request.customerId(),
                request.customerName(),
                plan,
                request.usageHours(),
                request.nonProfit()
        );

        Invoice invoice = billingService.generateInvoice(customer);

        InvoiceResponse response = new InvoiceResponse(
                invoice.customerId(),
                invoice.customerName(),
                invoice.planName(),
                invoice.usageHours(),
                invoice.baseAmount(),
                invoice.discountAmount(),
                invoice.finalAmount()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/plans")
    @Operation(summary = "List supported plans")
    public ResponseEntity<String> listPlans() {
        String plans = "Available plans:\n- FREE\n- STANDARD\n- ENTERPRISE";
        return ResponseEntity.ok(plans);
    }
}
