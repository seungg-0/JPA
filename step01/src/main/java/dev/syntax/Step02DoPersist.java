package dev.syntax;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dev.syntax.model.Book;

public class Step02DoPersist {
	 
	public static void main(String[] args) {
		
		// JPA �������̽�(java.persistence)���� �����ϴ� EntityManagerFactory��
		// Persistence Ŭ������ ���̹�����Ʈ ����ü�� �ν��Ͻ�ȭ
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");

		EntityManager manager = factory.createEntityManager();
		
		// ����ȭ(persist(entity))
		
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin(); // BEGIN;
			
			// Book ��ƼƼ ����, 30�� ���� ���������� transient ����(�񿵼�)
			// ����ȭ�Ǿ����� ����
			// ����ȭ(persist): ���Ӽ� ���ؽ�Ʈ���� �����ǰ� ���� ���� ����
			Book book = new Book("JPA", "����", new Date());
			
			manager.persist(book); // ����ȭ �������
			
			transaction.commit(); // COMMIT;
			// tx.commit() -> flush() -> INSERT Query �������
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close(); // �Ŵ��� ����, ���Ӽ� ���ؽ�Ʈ ����
			factory.close(); // ���� ����
		}
	}
}



