package dev.syntax.model.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CustomerRank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cs_rank")
	private int rank;
	
	@Column(name = "code_name")
	private String code_name;
	
	@OneToMany(mappedBy = "customerRank", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Customer> customers;

	public void addCustomer(Customer customer) {
		this.customers.add(customer);
		customer.setCustomerRank(this);
	}
	public CustomerRank(String code_name) {
		super();
		this.code_name = code_name;
		this.customers = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "CustomerRank [rank=" + rank + ", code_name=" + code_name + ", customers=" + customers + "]";
	}
}
