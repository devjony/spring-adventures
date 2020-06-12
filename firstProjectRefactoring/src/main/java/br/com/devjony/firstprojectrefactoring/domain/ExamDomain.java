package br.com.devjony.firstprojectrefactoring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class ExamDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3979699514556839697L;
	
	@EmbeddedId
	private StudentTestDomain studentTestDomain;
	
	@Column(name = "grade", nullable = false)
	private String grade;
	
	public StudentTestDomain setStudentTest() {
		return studentTestDomain;
	}
	
	public void setStudentTest(StudentTestDomain studentTestDomain) {
		this.studentTestDomain = studentTestDomain;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
