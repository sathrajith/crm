package com.crm.crm_web_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.crm_web_app.entity.Account;
import com.crm.crm_web_app.entity.AccountStatus;
import com.crm.crm_web_app.service.AccountService;



@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Display all accounts
    @GetMapping
    public String getAllAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "accounts"; // Render the 'accounts' view
    }

    // Display a specific account by ID
    @GetMapping("/{id}")
    public String getAccountById(@PathVariable Long id, Model model) {
        Optional<Account> account = accountService.getAccountById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            return "accountDetails"; // Render the 'accountDetails' view
        } else {
            return "redirect:/accounts"; // Redirect to accounts list if not found
        }
    }

    // Create or update an account
    @PostMapping("/save")
    public String saveAccount(@ModelAttribute Account account) {
        accountService.createOrUpdateAccount(account);
        return "redirect:/accounts"; // Redirect to accounts list after save
    }

    // Delete an account by ID
    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts"; // Redirect to accounts list after deletion
    }

    // Update account status
    @PostMapping("/updateStatus/{id}")
    public String updateAccountStatus(@PathVariable Long id, @RequestParam AccountStatus status) {
        accountService.updateAccountStatus(id, status);
        return "redirect:/accounts"; // Redirect to accounts list after status update
    }
    

    // Filter accounts by status
    @GetMapping("/filter")
    public String filterAccountsByStatus(@RequestParam String status, Model model) {
        List<Account> accounts = accountService.getAccountsByStatus(status);
        model.addAttribute("accounts", accounts);
        return "accounts"; // Render the 'accounts' view with filtered results
    }
}

