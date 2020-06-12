package br.com.devjony.firstprojectrefactoring.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class StudentDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9023925201084763091L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

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

    @Override
    public String toString() {
        return "StudentDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
