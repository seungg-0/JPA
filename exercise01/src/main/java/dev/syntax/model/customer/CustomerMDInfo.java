package dev.syntax.model.customer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CustomerMDInfo implements Serializable{
	
	@Id
	@Column(name = "MDDate")
	private Date date;
	
	@Id
	@Column(name = "MD_cs_num")
	private String customerNum;
	
	@ManyToOne
	@JoinColumn(name = "cs_id")
	private Customer customer;
	
	@Column(name = "rank_beforeMD")
	private int rankBefore;
	
	@Column(name = "rank_afterMD")
	private int rankAfter;

	public CustomerMDInfo(Date date, String customerNum, Customer customer, int rankBefore, int rankAfter) {
		super();
		this.date = date;
		this.customerNum = customerNum;
		this.customer = customer;
		this.rankBefore = rankBefore;
		this.rankAfter = rankAfter;
	}

	@Override
	public String toString() {
		return "CustomerMDInfo [date=" + date + ", customerNum=" + customerNum + ", customer=" + customer
				+ ", rankBefore=" + rankBefore + ", rankAfter=" + rankAfter + "]";
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		
	}

}
