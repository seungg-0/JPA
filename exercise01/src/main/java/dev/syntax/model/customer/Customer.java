package dev.syntax.model.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cs_id")
	private int id;
	
	@Column(name = "cs_name")
	private String name;
	
	@Column(name = "cs_address")
	private String address;
	
	@Column(name = "cs_phoneNum")
	private int phoneNum;
	
	@Column(name = "cs_gender")
	private char gender;

	@ManyToOne
	@JoinColumn(name = "cs_rank")
	private CustomerRank customerRank;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerMDInfo> customerMDInfos = new ArrayList<>();
	
	public void addCustomerMD(CustomerMDInfo customerMDInfo) {
		this.customerMDInfos.add(customerMDInfo);
		customerMDInfo.setCustomer(this);
	}
	
	public Customer(String name, String address, int phoneNum, char gender) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.gender = gender;
		this.customerMDInfos = new ArrayList<>();
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", phoneNum=" + phoneNum + ", gender="
				+ gender + ", customerRank=" + customerRank + ", customerMDInfos=" + customerMDInfos + "]";
	}

	public void setCustomerRank(CustomerRank customerRank) {
		this.customerRank = customerRank;
		
	}

	
	
	
	
}
