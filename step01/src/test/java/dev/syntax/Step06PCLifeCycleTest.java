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

	// 테스트를 위한 기본 구성
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");

	EntityManager manager = factory.createEntityManager();

	EntityTransaction transaction = manager.getTransaction();
	
	@Test
	void testPersist를_수행할_경우_엔티티가_영속화되고_commit이_수행될_경우_DB에_저장된다() {
		transaction.begin();
		
		Book jpaBook = new Book("JPA", "무명", new Date());
		manager.persist(jpaBook);
		
		transaction.commit();
		
		Book foundBook = manager.find(Book.class, jpaBook.getId());
		System.out.println(jpaBook == foundBook);
		assertEquals(jpaBook, foundBook); // 단정문 통해서 테스트 결과 확인
	}

	@Test
	void test한번_영속화된_엔티티는_다시_조회시_DB가_아닌_영속성컨텍스트에서_조회된다() {
		int id = 1;
		transaction.begin();
		Book founBook = manager.find(Book.class, id);
		System.out.println(founBook);
		transaction.commit();
		
		Book foundBookTwice = manager.find(Book.class, id);

		assertEquals(founBook, foundBookTwice);
	}

	@Test
	void test엔티티의_값을_수정하고_commit하면_변경감지가_발생되어_UPDATE쿼리가_수행된다() {
		int id = 1;
		transaction.begin();
		Book book = manager.find(Book.class, id);
		book.updateBookName("ABC"); // Book의 이름을 JPA에서 ABC로 변경
		transaction.commit();
		Book founBookAfterModify = manager.find(Book.class, id);
		
		System.out.println(book);
		System.out.println(founBookAfterModify);
		assertEquals(book, founBookAfterModify);
	}

	@Test
	void test만약_엔티티를_detach할경우_필드의_값을_변경해도_UPDATE쿼리가_수행되지_않는다() {
		fail("Not yet implemented");
	}

	@Test
	void testRemove를_수행할경우_PC와_DB에서_데이터가_제거된다() {
		fail("Not yet implemented");
	}

	@Test
	void test영속성컨텍스트를_초기화할경우_모든_엔티티가_제거됨() {
		fail("Not yet implemented");
	}

	@Test
	void test엔티티매니저가_제거됨() {
		fail("Not yet implemented");
	}

}
