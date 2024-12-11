package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Invoice;
import com.crm.crm_web_app.entity.InvoiceStatus;
import com.crm.crm_web_app.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoicerepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoicerepository) {
        this.invoicerepository = invoicerepository;
    }

    public Invoice saveInvoice(Invoice invoice) {
        validateInvoiceBeforeSave(invoice);
        Double finalAmount = invoice.getFinalAmount();
        invoice.setAmount(finalAmount);
        return invoicerepository.save(invoice);
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoicerepository.findById(id);
    }

    public List<Invoice> getAllInvoice() {
        return invoicerepository.findAll();
    }

    public List<Invoice> getInvoiceByCustomerId(Long customerId) {
        return invoicerepository.findByCustomerId(customerId);
    }

    public void deleteInvoiceById(Long id) {
        invoicerepository.deleteById(id);
    }

    public Invoice updateInvoiceStatus(Long invoiceId, String status) {
        Optional<Invoice> optionalInvoice = invoicerepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setStatus(InvoiceStatus.valueOf(status));
            invoice.setAmount(invoice.getFinalAmount()); // Apply discount if any
            return invoicerepository.save(invoice);
        }
        throw new RuntimeException("Invoice not found with Id: " + invoiceId);
    }

    public Invoice markInvoiceAsPaid(Long invoiceId, LocalDateTime paymentDate) {
        Optional<Invoice> optionalInvoice = invoicerepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setPaymentDate(paymentDate);
            invoice.updateInvoiceStatus();
            return invoicerepository.save(invoice);
        } else {
            throw new RuntimeException("Invoice not found with Id: " + invoiceId);
        }
    }

    private void validateInvoiceBeforeSave(Invoice invoice) {
        if (invoice.getPaymentDate() != null && invoice.getPaymentDate().isBefore(invoice.getIssueDate())) {
            throw new IllegalArgumentException("Payment date cannot be before issue date");
        }

        if (invoice.getDiscount() < 0 || invoice.getDiscount() > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
    }

    @Transactional
    public void bulkUpdateInvoiceStatus(List<Long> invoiceIds, String status) {
        for (Long invoiceId : invoiceIds) {
            updateInvoiceStatus(invoiceId, status);
        }
    }

    public List<Invoice> getInvoicesByStatus(String status) {
        return invoicerepository.findByStatus(status);
    }
}
