package com.crm.crm_web_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.crm_web_app.entity.Account;
import com.crm.crm_web_app.entity.AccountStatus;
import com.crm.crm_web_app.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Get all accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Get account by ID
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    // Create or update account
    public Account createOrUpdateAccount(Account account) {
        return accountRepository.save(account);
    }

    // Delete account
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    // Update account status
    public boolean canDeactivateAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        
        // Business logic: Ensure no active deals or invoices before deactivating
        if (!account.getDeals().isEmpty() || !account.getInvoices().isEmpty()) {
            return false; // Cannot deactivate if there are active deals or invoices
        }
        return true;
    }

    public Account updateAccountStatus(Long accountId, AccountStatus status) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        
        // Business logic to check status transition rules
        if (status == AccountStatus.INACTIVE && !canDeactivateAccount(accountId)) {
            throw new RuntimeException("Account cannot be deactivated because it has active deals or invoices.");
        }

        account.setStatus(status);
        return accountRepository.save(account);
    }

    // Get accounts by a certain filter (e.g., by status)
    public List<Account> getAccountsByStatus(String status) {
        return accountRepository.findByStatus(status);
    }
}

