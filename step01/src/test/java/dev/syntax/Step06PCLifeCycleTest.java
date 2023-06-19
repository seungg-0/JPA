package dev.syntax;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import dev.syntax.model.Book;

class Step06PCLifeCycleTest {

	// �׽�Ʈ�� ���� �⺻ ����
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");

	EntityManager manager = factory.createEntityManager();

	EntityTransaction transaction = manager.getTransaction();
	
	@Test
	void testPersist��_������_���_��ƼƼ��_����ȭ�ǰ�_commit��_�����_���_DB��_����ȴ�() {
		transaction.begin();
		
		Book jpaBook = new Book("JPA", "����", new Date());
		manager.persist(jpaBook);
		
		transaction.commit();
		
		Book foundBook = manager.find(Book.class, jpaBook.getId());
		System.out.println(jpaBook == foundBook);
		assertEquals(jpaBook, foundBook); // ������ ���ؼ� �׽�Ʈ ��� Ȯ��
	}

	@Test
	void test�ѹ�_����ȭ��_��ƼƼ��_�ٽ�_��ȸ��_DB��_�ƴ�_���Ӽ����ؽ�Ʈ����_��ȸ�ȴ�() {
		int id = 1;
		transaction.begin();
		Book founBook = manager.find(Book.class, id);
		System.out.println(founBook);
		transaction.commit();
		
		Book foundBookTwice = manager.find(Book.class, id);

		assertEquals(founBook, foundBookTwice);
	}

	@Test
	void test��ƼƼ��_����_�����ϰ�_commit�ϸ�_���氨����_�߻��Ǿ�_UPDATE������_����ȴ�() {
		int id = 1;
		transaction.begin();
		Book book = manager.find(Book.class, id);
		book.updateBookName("ABC"); // Book�� �̸��� JPA���� ABC�� ����
		transaction.commit();
		Book founBookAfterModify = manager.find(Book.class, id);
		
		System.out.println(book);
		System.out.println(founBookAfterModify);
		assertEquals(book, founBookAfterModify);
	}

	@Test
	void test����_��ƼƼ��_detach�Ұ��_�ʵ���_����_�����ص�_UPDATE������_�������_�ʴ´�() {
		int id = 1;
		transaction.begin();
		
		// id�� 1�� ��ƼƼ ��ȸ
		Book foundBook = manager.find(Book.class, id);
		foundBook.updateBookName("IDJ");
		
		manager.detach(foundBook); // foundBook ��ƼƼ�� PC���� �и�
		// update ���� ���� �ȵ�
		
		transaction.commit();
		
		Book foundBookTwice = manager.find(Book.class, id);
//		assertEquals(foundBook, foundBookTwice);
		assertNotEquals(foundBook, foundBookTwice);
	}

	@Test
	void testRemove��_�����Ұ��_PC��_DB����_�����Ͱ�_���ŵȴ�() {
		int id = 4;
		transaction.begin();
		
		Book foundBook = manager.find(Book.class, id);
		manager.remove(foundBook);
	
		transaction.commit();
		
		assertThrows(NullPointerException.class, () -> {
			
			// ������ remove�� �Ǿ��� ������ select�� ������ �������� �ʰ�, null�� �Ҵ� ��
			Book foundBookTwice = manager.find(Book.class, id); // foundBookTwice �� Null�� ��
			// null.updateBookName()�� �����߱� ������ nullPointer �߻� (�׽�Ʈ ����)
			foundBookTwice.updateBookName("Effective Unit Testing"); // Null �����ͷ� �޼��� ȣ��
			
			// PC�� ���������� SELECT�� �������� �ʱ� ������ ������ �ִ� ��ƼƼ�� �� �� true�� ���� �� ???
			
			System.out.println(foundBook);
			System.out.println(foundBookTwice);
			assertFalse(foundBook == foundBookTwice); // false�� ���̶�� ������
			
		});
	}

	@Test
	void test���Ӽ����ؽ�Ʈ��_�ʱ�ȭ�Ұ��_���_��ƼƼ��_���ŵ�() {
		int id = 4;
		Book foundBook = manager.find(Book.class, id);
		
		manager.clear(); // PC �� �� û����, ��ƼƼ ��� ����
		
		Book foundBookTwice = manager.find(Book.class, id);
		
		assertFalse(foundBook == foundBookTwice);
		
	}

	@Test
	void test��ƼƼ�Ŵ�����_���ŵ�() {
		EntityManager beforeClose = manager;
		System.out.println(beforeClose);
		// ��ƼƼ �Ŵ��� ��ü�� ���� -> PC(���Ӽ� ���ؽ�Ʈ)�� ���ŵ� 
		manager.close();
		EntityManager afterClose = manager;

		System.out.println(afterClose);
		
	}

}
