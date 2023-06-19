package dev.syntax;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dev.syntax.model.Book;

public class Step05DeleteEntity {
	// ��ƼƼ ����, DB �� ���ڵ� ����
	// DELETE ����
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			// transaction �ȿ��� ���� ���õ� �ڵ带 �ִ� ���� ���
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
