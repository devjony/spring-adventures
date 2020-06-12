package br.com.devjony.firstprojectrefactoring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class QuestionDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4228863346233508471L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToOne
	@JoinColumn(name = "id_answer")
	private AnswerDomain answer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AnswerDomain getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerDomain answer) {
		this.answer = answer;
	}
}
