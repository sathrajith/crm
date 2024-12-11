package com.crm.crm_web_app.controller;

import com.crm.crm_web_app.entity.Invoice;
import com.crm.crm_web_app.entity.InvoiceStatus;
import com.crm.crm_web_app.service.InvoiceService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Create or update an invoice
    @PostMapping
    public ResponseEntity<Invoice> createOrUpdateInvoice(@Valid @RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.saveInvoice(invoice);
        return ResponseEntity.ok(savedInvoice);
    }

    // Get invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all invoices
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoice();
        return ResponseEntity.ok(invoices);
    }

    // Get invoices by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Invoice>> getInvoicesByCustomerId(@PathVariable Long customerId) {
        List<Invoice> invoices = invoiceService.getInvoiceByCustomerId(customerId);
        return ResponseEntity.ok(invoices);
    }

    // Delete an invoice
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoiceById(id);
        return ResponseEntity.noContent().build();
    }

    // Update invoice status
    @PutMapping("/{id}/status")
    public ResponseEntity<Invoice> updateInvoiceStatus(@PathVariable Long id, @RequestBody InvoiceStatusRequest statusRequest) {
        Invoice updatedInvoice = invoiceService.updateInvoiceStatus(id, statusRequest.getStatus());
        return ResponseEntity.ok(updatedInvoice);
    }

    // Mark an invoice as paid
    @PutMapping("/{id}/pay")
    public ResponseEntity<Invoice> markInvoiceAsPaid(@PathVariable Long id, @RequestParam String paymentDate) {
        LocalDateTime paymentDateTime = LocalDateTime.parse(paymentDate);
        Invoice updatedInvoice = invoiceService.markInvoiceAsPaid(id, paymentDateTime);
        return ResponseEntity.ok(updatedInvoice);
    }

    // Bulk update invoice status
    @PutMapping("/bulk/status")
    public ResponseEntity<Void> bulkUpdateInvoiceStatus(@RequestBody List<Long> invoiceIds, @RequestParam String status) {
        invoiceService.bulkUpdateInvoiceStatus(invoiceIds, status);
        return ResponseEntity.noContent().build();
    }

    // DTO for invoice status update
    public static class InvoiceStatusRequest {
        private String status;

        // Getter and Setter
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
