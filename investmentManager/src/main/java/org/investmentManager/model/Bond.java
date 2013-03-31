package org.investmentManager.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BOND")
public class Bond extends Investment implements Serializable {

	private final static int LINE_SIZE = 12;
	private final static int NAME_INDEX = 1;
	private final static int QTY_INDEX = 2;
	private final static int LAST_QUOTE_AMOUNT_INDEX = 4;
	private final static int MONEY_AMOUNT_INDEX = 8;
	private final static int LAST_QUOTE_DATETIME_INDEX = 5;

	public Bond() {
		super();
	}

	public Bond(String name) {
		super(name);
	}

	public Bond(String name, Double qty, Double lastQuoteAmount,
			Double moneyAmount, Date lastQuoteDatetime) {
		super(name, qty, lastQuoteAmount, moneyAmount, lastQuoteDatetime);
	}

	public static ArrayList<Bond> parseLines(String lines)
			throws ParseException {
		ArrayList<Bond> bonds = new ArrayList<Bond>();
		String[] splitedStr = lines.split("\\t");
		String buffer = new String();

		for (int i = 0; i < LINE_SIZE; i++) {
			buffer = buffer + splitedStr[i] + "\\t";
			if (i % (LINE_SIZE - 1) == 0) {
				Bond bond = parseLine(buffer.split("\\t"));
				bonds.add(bond);
				System.out.println(bond);
				buffer = new String();
			}
		}

		return bonds;
	}

	private static Bond parseLine(String[] line) throws ParseException {
		DecimalFormat df = new DecimalFormat("###,###.##");

		Number qty = (Number) df.parse(line[QTY_INDEX]);
		Number lastQuoteAmount = (Number) df
				.parse(line[LAST_QUOTE_AMOUNT_INDEX]);
		Number moneyAmount = (Number) df.parse(line[MONEY_AMOUNT_INDEX]);
		Date lastQuoteDatetime = new Date(line[LAST_QUOTE_DATETIME_INDEX]);

		return new Bond(line[NAME_INDEX], qty.doubleValue(),
				lastQuoteAmount.doubleValue(), moneyAmount.doubleValue(),
				lastQuoteDatetime);
	}

	@Override
	public String toString() {
		return "Bond [getName()=" + getName() + ", getMoneyAmount()="
				+ getMoneyAmount() + "]";
	}

}
