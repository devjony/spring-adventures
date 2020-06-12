package br.com.devjony.firstprojectrefactoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devjony.firstprojectrefactoring.domain.AnswerDomain;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerDomain, Integer> {

}
