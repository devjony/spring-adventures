package br.com.devjony.firstprojectrefactoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devjony.firstprojectrefactoring.domain.StudentDomain;

@Repository
public interface StudentRepository extends JpaRepository<StudentDomain, Integer> {
	
	StudentDomain findByEmailAndPassword(String email, String password);
	
}
