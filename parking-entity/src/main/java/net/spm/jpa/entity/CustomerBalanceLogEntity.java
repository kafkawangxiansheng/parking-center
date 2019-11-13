package net.spm.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vincent on 02/10/2018
 */
@Entity
@Table(name = "customer_balance_log")
public class CustomerBalanceLogEntity {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private int ttype;
	
	@Column(name="cus_id")
	private int cusId;
	
	@Column(name="before_balance")
	private double beforeBalance;
	
	private double amount;
	
	@Column(name="after_balance")
	private double afterBalance;
	
	@Column(name="ref_doc")
	private String refDoc;
	
	@Column(name="ref_desc")
	private String refDesc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTtype() {
		return ttype;
	}

	public void setTtype(int ttype) {
		this.ttype = ttype;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public double getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(double beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(double afterBalance) {
		this.afterBalance = afterBalance;
	}

	public String getRefDoc() {
		return refDoc;
	}

	public void setRefDoc(String refDoc) {
		this.refDoc = refDoc;
	}

	public String getRefDesc() {
		return refDesc;
	}

	public void setRefDesc(String refDesc) {
		this.refDesc = refDesc;
	}
	
}
