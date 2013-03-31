package org.investmentManager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FUND")
public class Fund extends Investment implements Serializable {
	
	public Fund() {
		super();
	}

}
