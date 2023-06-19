package dev.syntax;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dev.syntax.model.Book;

public class Step05DeleteEntity {
	// 엔티티 제거, DB 내 레코드 제거
	// DELETE 쿼리
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			// transaction 안에는 서로 관련된 코드를 넣는 것이 깔끔
			transaction.begin();
			Book book = manager.find(Book.class, 1);
			manager.remove(book);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			manager.close();
			factory.close();
		}
	}
}
