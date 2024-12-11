package com.crm.crm_web_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_web_app.entity.Deal;
import com.crm.crm_web_app.service.DealService;

import java.util.List;
@Controller
@RequestMapping("/deals")
public class DealController {

    @Autowired
    private DealService dealService;

    @GetMapping
    public String getAllDeals(Model model) {
        List<Deal> deals = dealService.getAllDeals();
        model.addAttribute("deals", deals);
        return "deals";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable Long id) {
        return dealService.getDealById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createDeal(@ModelAttribute Deal deal) {
        dealService.createOrUpdateDeal(deal);
        return "redirect:/deals";
    }

    @PostMapping("/delete/{id}")
    public String deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return "redirect:/deals";
    }

    @PostMapping("/updateStage/{id}")
    public String updateStage(@PathVariable Long id, @RequestParam String stage) {
        dealService.updateDealStage(id, stage);
        return "redirect:/deals";
    }

    @PostMapping("/updatePriority/{id}")
    public String updatePriority(@PathVariable Long id, @RequestParam String priority) {
        dealService.updatePriority(id, priority);
        return "redirect:/deals";
    }
} // End of DealController