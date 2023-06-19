package dev.syntax;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


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
		fail("Not yet implemented");
	}

	@Test
	void testRemove��_�����Ұ��_PC��_DB����_�����Ͱ�_���ŵȴ�() {
		fail("Not yet implemented");
	}

	@Test
	void test���Ӽ����ؽ�Ʈ��_�ʱ�ȭ�Ұ��_���_��ƼƼ��_���ŵ�() {
		fail("Not yet implemented");
	}

	@Test
	void test��ƼƼ�Ŵ�����_���ŵ�() {
		fail("Not yet implemented");
	}

}
