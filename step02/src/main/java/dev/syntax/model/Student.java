package dev.syntax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Student(String studentName) {
		super();
		this.studentName = studentName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int id;

	@Column(name = "student_name", nullable = false)
	private String studentName;
	
	@ManyToOne // �������� �л��� �ϳ��� ������ ���� �� �ִ�. 
	// Many : �л�, One : ����
	@JoinColumn(name = "major_id")
	// -> �ܷ�Ű ���� �� ���, name �Ӽ��� ������ �ܷ�Ű �̸� ����
	private Major major;

	// �⺻ ������
	protected Student() {
	}

	public Student(int id, String studentName) { 
		super();
		this.id = id;
		this.studentName = studentName;
	}

	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + "]";
	}

}