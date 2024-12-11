package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Investment;
import com.crm.crm_web_app.entity.Profit;
import com.crm.crm_web_app.repository.InvestmentRepository;
import com.crm.crm_web_app.repository.ProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final ProfitRepository profitRepository;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository, ProfitRepository profitRepository) {
        this.investmentRepository = investmentRepository;
        this.profitRepository = profitRepository;
    }

    // Create or update an investment
    public Investment saveInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    // Get an investment by its ID
    public Optional<Investment> getInvestmentById(Long id) {
        return investmentRepository.findById(id);
    }

    // Get all investments
    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    // Delete an investment by its ID
    public void deleteInvestmentById(Long id) {
        investmentRepository.deleteById(id);
    }

    // Calculate ROI (Return on Investment)
    public BigDecimal calculateROI(Long investmentId) {
        Optional<Investment> investmentOptional = investmentRepository.findById(investmentId);
        if (investmentOptional.isPresent()) {
            Investment investment = investmentOptional.get();
            BigDecimal amountInvested = investment.getAmount();

            // Example: Retrieve profit related to the investment. Here, assuming profit is tracked in the Profit entity.
            Optional<Profit> profitOptional = profitRepository.findByInvestment(investment); 
            if (profitOptional.isPresent()) {
                BigDecimal profit = profitOptional.get().getAmount();
                // Calculate ROI as (Profit / Amount Invested)
                return profit.divide(amountInvested, 2, BigDecimal.ROUND_HALF_UP);
            }
        }
        return BigDecimal.ZERO; // Return 0 if investment or profit not found
    }
}
