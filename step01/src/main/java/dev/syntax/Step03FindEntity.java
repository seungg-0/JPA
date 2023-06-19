package dev.syntax;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dev.syntax.model.Book;

public class Step03FindEntity {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");

		EntityManager manager = factory.createEntityManager();
		
		// SELECT�� transaction �ʿ� ����
		// id�� 1�� ���ڵ�(��ƼƼ) ��ȸ
		// Record: DB �ƶ������� ������, �� ���� Ʃ��, ��(Row)
		// ��ƼƼ : Java, JPA �ƶ����� �ϳ��� �ν��Ͻ�
		Book book = manager.find(Book.class, 1);
		System.out.println(book);

	}

}
