package com.crm.crm_web_app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.crm_web_app.entity.Deal;
import com.crm.crm_web_app.repository.DealRepository;

import jakarta.transaction.Transactional;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public Optional<Deal> getDealById(Long id) {
        return dealRepository.findById(id);
    }

    @Transactional
    public Deal createOrUpdateDeal(Deal deal) {
        validateDeal(deal);
        return dealRepository.save(deal);
    }

    @Transactional
    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }

    public void updateDealStage(Long dealId, String newStage) {
        Optional<Deal> optionalDeal = getDealById(dealId);
        if (optionalDeal.isPresent()) {
            Deal deal = optionalDeal.get();
            deal.setStage(newStage);
            dealRepository.save(deal);
        } else {
            throw new IllegalArgumentException("Deal not found with ID: " + dealId);
        }
    }

    public void updatePriority(Long dealId, String newPriority) {
        Optional<Deal> optionalDeal = getDealById(dealId);
        if (optionalDeal.isPresent()) {
            Deal deal = optionalDeal.get();
            deal.setPriority(newPriority);
            dealRepository.save(deal);
        } else {
            throw new IllegalArgumentException("Deal not found with ID: " + dealId);
        }
    }

    private void validateDeal(Deal deal) {
        if (deal.getValue() == null || deal.getValue() <= 0) {
            throw new IllegalArgumentException("Deal value must be positive.");
        }
        if (deal.getExpectedCloseDate() == null || deal.getExpectedCloseDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expected close date must be in the future.");
        }
    }
}