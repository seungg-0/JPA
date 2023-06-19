package dev.syntax;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import dev.syntax.model.Major;
import dev.syntax.model.Student;

class Step02RDBWayTest {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("step02");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	@Test
	void testSave() {
		transaction.begin();
		
		// 학과 데이터 저장
		Major major = new Major("컴퓨터 공학");
		manager.persist(major);
		// 학생 1 생성, 저장
		Student kim = new Student("민수");
		// 학생에게 전공 추가
		kim.setMajor(major);	
		manager.persist(kim);
		
		// 학생 1 생성, 저장
		Student yoon = new Student("동열");
		// 학생에게 전공 추가
		yoon.setMajor(major);	
		manager.persist(yoon);
		
		transaction.commit();
	}

	@Test
	void testRead() {
		// id가 1인 학생 엔티티 조회
		Student student = manager.find(Student.class, 1);
		System.out.println(student);
		
		Major major = student.getMajor();
		System.out.println(major);
		
		System.out.println("전공 이름: " + major.getMajorName());
	
	}

	@Test
	void testUpdateRelation() { // 전공을 변경
		transaction.begin(); // 값을 변경하는 것이기 때문에 transaction 필요
		
		// 새로운 전공 국문학과 생성
		Major major = new Major("국문학과");
		manager.persist(major);
		
		// 학번이 1인 학생(민수)의 전공을 국문학과로 변경
		Student student = manager.find(Student.class, 1);
		student.setMajor(major); // 국문학과로 변경
		// 더티체킹 발생, 적용됨
		
		transaction.commit();
	}

	@Test
	void testDeleteEntity() { // 전공(연관관계) 제거
		// 국문학과 제거
		Student student = manager.find(Student.class, 5);
		student.setMajor(null); // 다른과로 가세요 !
		System.out.println("~~~");
		Student student1 = manager.find(Student.class, 6);
//		student1.setMajor(1123456456456); // 다른과로 가세요 !
		Major major = manager.find(Major.class, 3);
		manager.remove(major);
		transaction.begin();
		
		
		transaction.commit();
		
		
		// 왜 안되지?
//		int id = 2;
//		transaction.begin();
//		
//		Major major = manager.find(Major.class, 1);
//		manager.remove(major);
//	
//		transaction.commit();
		

	
	}

}
