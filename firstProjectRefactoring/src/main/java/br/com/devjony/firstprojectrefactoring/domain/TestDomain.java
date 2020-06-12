package br.com.devjony.firstprojectrefactoring.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class TestDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2724688244826171184L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @OneToMany
    @JoinColumn(name = "id_test")
    private List<QuestionDomain> questions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	public List<QuestionDomain> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDomain> questions) {
		this.questions = questions;
	}

	@Override
    public String toString() {
        return "TestDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
