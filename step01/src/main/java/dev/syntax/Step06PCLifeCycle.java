package dev.syntax;

public class Step06PCLifeCycle {

	
		// Entity LifeCycle Ȯ�ο� �׽�Ʈ �ڵ�
		
		// In JS, todo.test.js || todo.sepc.js
		// In JAVA, ������ test������ �ۼ�
		
		// 1. Save the data(entity) - manager.persist(entity) - INSERT SQL
		void persist��_������_���_��ƼƼ��_����ȭ�ǰ�_commit��_�����_���_DB��_����ȴ�() {
			
		}

		// 2. Retrieve the record - manager.find(Entity.class, id) - SELECT SQL
		void �ѹ�_����ȭ��_��ƼƼ��_�ٽ�_��ȸ��_DB��_�ƴ�_���Ӽ����ؽ�Ʈ����_��ȸ�ȴ�() {
			
		}

		// 3. Update the record - not supported with method - UPDATE SQL
		void ��ƼƼ��_����_�����ϰ�_commit�ϸ�_���氨����_�߻��Ǿ�_UPDATE������_����ȴ�() {
			
		}

		// 4. Delete the record - manager.remove(entity) - DELETE SQL
		void ����_��ƼƼ��_detach�Ұ��_�ʵ���_����_�����ص�_UPDATE������_�������_�ʴ´�() {
			
		}

		// etc lifecycle

		// 5. Detach an entity from the Persistence Context - manager.detach(entity)
		void remove��_�����Ұ��_PC��_DB����_�����Ͱ�_���ŵȴ�() {
			
		}

		// 6. Remove the EntityManager & PC - manager.close()
		void ���Ӽ����ؽ�Ʈ��_�ʱ�ȭ�Ұ��_���_��ƼƼ��_���ŵ�() {
			
		}
		
		void ��ƼƼ�Ŵ�����_���ŵ�() {
		
		}

	}


