package dev.syntax;

import dev.syntax.model.Major;
import dev.syntax.model.Student;

public class Step01OOPWay {
	// RDB없이 객체지향만으로 Student와 Major의 관계 구현
	public static void main(String[] args) {
		Student hwang = new Student(1, "철원");
		Student han = new Student(2, "윤서");
		
		Major computer = new Major(1, "Computer Science");
		
		hwang.setMajor(computer); // 철원님의 전공을 컴공으로 지정
		han.setMajor(computer);
		
		Major majorOfHwang = hwang.getMajor();
		System.out.println(majorOfHwang);

	}

}
