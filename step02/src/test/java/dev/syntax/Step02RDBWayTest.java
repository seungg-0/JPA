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
		
		// �а� ������ ����
		Major major = new Major("��ǻ�� ����");
		manager.persist(major);
		// �л� 1 ����, ����
		Student kim = new Student("�μ�");
		// �л����� ���� �߰�
		kim.setMajor(major);	
		manager.persist(kim);
		
		// �л� 1 ����, ����
		Student yoon = new Student("����");
		// �л����� ���� �߰�
		yoon.setMajor(major);	
		manager.persist(yoon);
		
		transaction.commit();
	}

	@Test
	void testRead() {
		// id�� 1�� �л� ��ƼƼ ��ȸ
		Student student = manager.find(Student.class, 1);
		System.out.println(student);
		
		Major major = student.getMajor();
		System.out.println(major);
		
		System.out.println("���� �̸�: " + major.getMajorName());
	
	}

	@Test
	void testUpdateRelation() { // ������ ����
		transaction.begin(); // ���� �����ϴ� ���̱� ������ transaction �ʿ�
		
		// ���ο� ���� �����а� ����
		Major major = new Major("�����а�");
		manager.persist(major);
		
		// �й��� 1�� �л�(�μ�)�� ������ �����а��� ����
		Student student = manager.find(Student.class, 1);
		student.setMajor(major); // �����а��� ����
		// ��Ƽüŷ �߻�, �����
		
		transaction.commit();
	}

	@Test
	void testDeleteEntity() { // ����(��������) ����
		// �����а� ����
		Student student = manager.find(Student.class, 5);
		student.setMajor(null); // �ٸ����� ������ !
		System.out.println("~~~");
		Student student1 = manager.find(Student.class, 6);
//		student1.setMajor(1123456456456); // �ٸ����� ������ !
		Major major = manager.find(Major.class, 3);
		manager.remove(major);
		transaction.begin();
		
		
		transaction.commit();
		
		
		// �� �ȵ���?
//		int id = 2;
//		transaction.begin();
//		
//		Major major = manager.find(Major.class, 1);
//		manager.remove(major);
//	
//		transaction.commit();
		

	
	}

}
