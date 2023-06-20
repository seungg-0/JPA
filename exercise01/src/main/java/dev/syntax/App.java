package dev.syntax;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dev.syntax.model.customer.Customer;
import dev.syntax.model.customer.CustomerMDInfo;
import dev.syntax.model.customer.CustomerRank;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("exercise01");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		// °í°´µî±Þ Entity »ý¼º
		CustomerRank customerRank = new CustomerRank("abcd");
		manager.persist(customerRank);
		
		// °í°´ Entity »ý¼º
		Customer customer = new Customer("Àº¿±", "°­¸ª", 01026146050, 'M');
		customerRank.addCustomer(customer);
		manager.persist(customer);
		
		// °í°´Á¤º¸º¯°æ±â·Ï Entity »ý¼º
		CustomerMDInfo customerMDInfo = new CustomerMDInfo(new Date(), "123", customer, 1, 5);
		customer.addCustomerMD(customerMDInfo);
		manager.persist(customerMDInfo);
		
		transaction.commit();
		

	}

}
