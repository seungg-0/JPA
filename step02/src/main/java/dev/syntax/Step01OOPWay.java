package dev.syntax;

import dev.syntax.model.Major;
import dev.syntax.model.Student;

public class Step01OOPWay {
	// RDB���� ��ü���⸸���� Student�� Major�� ���� ����
	public static void main(String[] args) {
		Student hwang = new Student(1, "ö��");
		Student han = new Student(2, "����");
		
		Major computer = new Major(1, "Computer Science");
		
		hwang.setMajor(computer); // ö������ ������ �İ����� ����
		han.setMajor(computer);
		
		Major majorOfHwang = hwang.getMajor();
		System.out.println(majorOfHwang);

	}

}
