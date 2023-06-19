package dev.syntax;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dev.syntax.model.Book;

public class Step04UpdateEntity {
	// ��ƼƼ ��(�ڹ� entity Ŭ������ �ʵ�, DB �÷�)�� ����(����)
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin(); // BEGIN
			Book book = manager.find(Book.class, 1);

			// JPA������ ������ UPDATE�� ���� �޼��带 �������� ����
			book.updateBookName("ABC"); // Book�� �̸��� JPA���� ABC�� ����
//			manager.persist(book); // �ٽ� ����ȭ
			transaction.commit(); // COMMIT;
//			tx.commit()�� ���� flush()�� ȣ��� ���, DB ���� ���� ���� ������ ���� �����
//
//			���Ӱ� ����� ��ƼƼ(`bookName: ABC`) �� ���� ���� �� ���� ��ƼƼ(`bookName: JPA`)
//			bookName�� �ٸ��� ������ �ش� ���� �����ϴ� UPDATE ������ �ڵ����� �����Ǿ� tx.commit()�� ����� ��� ������ DB�� ����, �����
			
			
		} catch (Exception e) {
		
		}finally {
			manager.clear();
			factory.close();
		}

	}

}
