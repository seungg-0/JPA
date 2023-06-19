package dev.syntax;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dev.syntax.model.Book;

public class Step04UpdateEntity {
	// 엔티티 값(자바 entity 클래스의 필드, DB 컬럼)을 변경(수정)
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin(); // BEGIN
			Book book = manager.find(Book.class, 1);

			// JPA에서는 별도의 UPDATE를 위한 메서드를 제공하지 않음
			book.updateBookName("ABC"); // Book의 이름을 JPA에서 ABC로 변경
//			manager.persist(book); // 다시 영속화
			transaction.commit(); // COMMIT;
//			tx.commit()을 통해 flush()가 호출될 경우, DB 적용 전에 변경 감지가 먼저 수행됨
//
//			새롭게 변경된 엔티티(`bookName: ABC`) ← 변경 감지 → 기존 엔티티(`bookName: JPA`)
//			bookName이 다르기 때문에 해당 값을 수정하는 UPDATE 쿼리가 자동으로 생성되어 tx.commit()이 수행될 경우 쿼리가 DB에 전달, 수행됨
			
			
		} catch (Exception e) {
		
		}finally {
			manager.clear();
			factory.close();
		}

	}

}
