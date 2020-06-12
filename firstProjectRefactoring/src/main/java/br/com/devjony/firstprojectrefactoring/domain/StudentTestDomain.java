package br.com.devjony.firstprojectrefactoring.domain;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class StudentTestDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4657755720269359436L;

	@ManyToOne
	private StudentDomain studentDomain;
	
	@ManyToOne
	private TestDomain testDomain;

    public StudentDomain getStudent() {
        return studentDomain;
    }

    public void setStudent(StudentDomain studentDomain) {
    	this.studentDomain = studentDomain;
    }
    
    public TestDomain getTest() {
		return testDomain;
	}
    
    public void setTest(TestDomain testDomain) {
		this.testDomain = testDomain;
	}
}
