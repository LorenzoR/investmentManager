package org.investmentManager.service;

import java.util.List;

import org.investmentManager.model.Stock;

public interface StockService {
	
	public List<String> getStocksName();
	
	public List<Stock> getStocks();
	
	public List<Stock> getStocks(String name, int first, int count);

	public Long insertStock(Stock stock);

}
