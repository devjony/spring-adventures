package br.com.devjony.firstprojectrefactoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devjony.firstprojectrefactoring.domain.ExamDomain;
import br.com.devjony.firstprojectrefactoring.domain.StudentTestDomain;

@Repository
public interface ExamRepository extends JpaRepository<ExamDomain, StudentTestDomain> {

}
