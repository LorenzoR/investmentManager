package org.investmentManager.service;

import java.util.List;

import org.investmentManager.model.Investment;
import org.investmentManager.model.Stock;

public interface InvestmentService {

	public List<String> getInvestmentsName();

	public List<Investment> getInvestments();

	public List<Investment> getInvestments(String name, int first, int count);

	public Long insertInvestment(Investment investment);

}
