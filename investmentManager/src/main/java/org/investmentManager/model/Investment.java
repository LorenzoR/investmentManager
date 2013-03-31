package org.investmentManager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Investment implements Serializable {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Basic
	private String name;
	
	@Basic
	private Double qty;
	
	@Basic
	private Double lastQuoteAmount;
	
	@Basic
	private Double moneyAmount;
	
	@Basic
	private Date lastQuoteDatetime;

	public Investment() {
		super();
	}

	public Investment(String name) {
		super();
		this.name = name;
	}

	public Investment(String name, Double qty, Double lastQuoteAmount,
			Double moneyAmount, Date lastQuoteDatetime) {
		super();
		this.name = name;
		this.qty = qty;
		this.lastQuoteAmount = lastQuoteAmount;
		this.moneyAmount = moneyAmount;
		this.lastQuoteDatetime = lastQuoteDatetime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getLastQuoteAmount() {
		return lastQuoteAmount;
	}

	public void setLastQuoteAmount(Double lastQuoteAmount) {
		this.lastQuoteAmount = lastQuoteAmount;
	}

	public Double getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(Double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public Date getLastQuoteDatetime() {
		return lastQuoteDatetime;
	}

	public void setLastQuoteDatetime(Date lastQuoteDatetime) {
		this.lastQuoteDatetime = lastQuoteDatetime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lastQuoteAmount == null) ? 0 : lastQuoteAmount.hashCode());
		result = prime
				* result
				+ ((lastQuoteDatetime == null) ? 0 : lastQuoteDatetime
						.hashCode());
		result = prime * result
				+ ((moneyAmount == null) ? 0 : moneyAmount.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((qty == null) ? 0 : qty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investment other = (Investment) obj;
		if (lastQuoteAmount == null) {
			if (other.lastQuoteAmount != null)
				return false;
		} else if (!lastQuoteAmount.equals(other.lastQuoteAmount))
			return false;
		if (lastQuoteDatetime == null) {
			if (other.lastQuoteDatetime != null)
				return false;
		} else if (!lastQuoteDatetime.equals(other.lastQuoteDatetime))
			return false;
		if (moneyAmount == null) {
			if (other.moneyAmount != null)
				return false;
		} else if (!moneyAmount.equals(other.moneyAmount))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (qty == null) {
			if (other.qty != null)
				return false;
		} else if (!qty.equals(other.qty))
			return false;
		return true;
	}

}
