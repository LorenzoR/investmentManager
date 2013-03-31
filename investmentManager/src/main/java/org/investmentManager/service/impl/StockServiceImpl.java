package org.investmentManager.service.impl;

import java.util.List;

import org.investmentManager.model.Stock;
import org.investmentManager.persistence.StockDao;
import org.investmentManager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDao itemDao;
	
	public StockServiceImpl() {
		
	}
	
	public StockDao getItemDao() {
		return this.itemDao;
	}
	
	public void setItemDao(StockDao itemDao) {
		this.itemDao = itemDao;
	}
	
	@Override
	public List<String> getStocksName() {
		return itemDao.getStocksName();
	}
	
	@Override
	public List<Stock> getStocks() {
		return itemDao.getStocks();
	}

	@Override
	public Long insertStock(Stock stock) {
		return itemDao.insertStock(stock);
	}

	@Override
	public List<Stock> getStocks(String name, int first, int count) {
		return itemDao.getStocks(name, first, count);
	}

}
