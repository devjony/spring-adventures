package br.com.devjony.firstprojectrefactoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devjony.firstprojectrefactoring.domain.StudentDomain;
import br.com.devjony.firstprojectrefactoring.repository.StudentRepository;

@Service
public class LoginService {

	@Autowired
	private StudentRepository studentRepository;
	
	public boolean login(StudentDomain studentDomain) {
		StudentDomain findStudent = studentRepository.findByEmailAndPassword(studentDomain.getEmail(), studentDomain.getPassword());
		
		return (findStudent == null);
	}
}
