package org.investmentManager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock extends Investment implements Serializable {

	private static final int LINE_SIZE = 12;
	private static final int NAME_INDEX = 1;
	private static final int QTY_INDEX = 2;
	private static final int LAST_QUOTE_AMOUNT_INDEX = 4;
	private static final int MONEY_AMOUNT_INDEX = 6;
	private static final int LAST_QUOTE_DATETIME_INDEX = 8;

	public Stock() {
		super();
	}
	
	public Stock(String name) {
		super(name);
	}

	public Stock(String name, Double qty, Double lastQuoteAmount,
			Double moneyAmount, Date lastQuoteDatetime) {
		super(name, qty, lastQuoteAmount, moneyAmount, lastQuoteDatetime);
	}

	public ArrayList<Stock> parseLines(String lines) {
		String[] splitedStr = lines.split("\\t");
		String buffer = new String();

		for (int i = 0; i < this.LINE_SIZE; i++) {
			buffer = buffer + splitedStr[i] + "\\t";
			if (i % (this.LINE_SIZE - 1) == 0) {
				parseLine(buffer);
				buffer = new String();
			}
		}

		return null;
	}

	public Stock parseLine(String line) {
		return null;
	}

	public String toString() {
		return super.getName() + " - " + super.getMoneyAmount();
	}

}
