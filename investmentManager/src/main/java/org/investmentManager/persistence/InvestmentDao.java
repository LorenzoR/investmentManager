package org.investmentManager.persistence;

import java.util.List;

import org.investmentManager.model.Investment;
import org.investmentManager.model.Stock;

public interface InvestmentDao {

	public List<String> getInvestmentsName();
	
	public List<Investment> getInvestments();
	
	public List<Investment> getInvestments(String name, int first, int count);

	public Long insertInvestment(Investment investment);

}
