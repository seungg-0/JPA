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
		int id = 1;
		transaction.begin();
		
		// id가 1인 엔티티 조회
		Book foundBook = manager.find(Book.class, id);
		foundBook.updateBookName("IDJ");
		
		manager.detach(foundBook); // foundBook 엔티티를 PC에서 분리
		// update 쿼리 수행 안됨
		
		transaction.commit();
		
		Book foundBookTwice = manager.find(Book.class, id);
//		assertEquals(foundBook, foundBookTwice);
		assertNotEquals(foundBook, foundBookTwice);
	}

	@Test
	void testRemove를_수행할경우_PC와_DB에서_데이터가_제거된다() {
		int id = 4;
		transaction.begin();
		
		Book foundBook = manager.find(Book.class, id);
		manager.remove(foundBook);
	
		transaction.commit();
		
		assertThrows(NullPointerException.class, () -> {
			
			// 위에서 remove가 되었기 때문에 select를 보내도 존재하지 않고, null이 할당 됨
			Book foundBookTwice = manager.find(Book.class, id); // foundBookTwice 는 Null이 됨
			// null.updateBookName()을 수행했기 때문에 nullPointer 발생 (테스트 성공)
			foundBookTwice.updateBookName("Effective Unit Testing"); // Null 포인터로 메서드 호출
			
			// PC에 남아있으면 SELECT를 수행하지 않기 때문에 기존에 있던 엔티티와 비교 시 true가 나올 것 ???
			
			System.out.println(foundBook);
			System.out.println(foundBookTwice);
			assertFalse(foundBook == foundBookTwice); // false일 것이라는 단정문
			
		});
	}

	@Test
	void test영속성컨텍스트를_초기화할경우_모든_엔티티가_제거됨() {
		int id = 4;
		Book foundBook = manager.find(Book.class, id);
		
		manager.clear(); // PC 한 번 청소해, 엔티티 모두 지워
		
		Book foundBookTwice = manager.find(Book.class, id);
		
		assertFalse(foundBook == foundBookTwice);
		
	}

	@Test
	void test엔티티매니저가_제거됨() {
		EntityManager beforeClose = manager;
		System.out.println(beforeClose);
		// 엔티티 매니저 자체를 제거 -> PC(영속성 컨텍스트)도 제거됨 
		manager.close();
		EntityManager afterClose = manager;

		System.out.println(afterClose);
		
	}

}
