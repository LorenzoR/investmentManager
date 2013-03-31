package org.investmentManager.service.impl;

import java.util.List;

import org.investmentManager.model.Investment;
import org.investmentManager.model.Stock;
import org.investmentManager.persistence.InvestmentDao;
import org.investmentManager.persistence.StockDao;
import org.investmentManager.service.InvestmentService;
import org.investmentManager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	private InvestmentDao itemDao;
	
	public InvestmentServiceImpl() {
		
	}
	
	public InvestmentDao getItemDao() {
		return this.itemDao;
	}
	
	public void setItemDao(InvestmentDao itemDao) {
		this.itemDao = itemDao;
	}
	
	@Override
	public List<String> getInvestmentsName() {
		return itemDao.getInvestmentsName();
	}
	
	@Override
	public List<Investment> getInvestments() {
		return itemDao.getInvestments();
	}

	@Override
	public Long insertInvestment(Investment investment) {
		return itemDao.insertInvestment(investment);
	}

	@Override
	public List<Investment> getInvestments(String name, int first, int count) {
		return itemDao.getInvestments(name, first, count);
	}

}
