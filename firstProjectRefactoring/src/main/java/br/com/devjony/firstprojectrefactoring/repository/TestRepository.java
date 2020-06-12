package br.com.devjony.firstprojectrefactoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devjony.firstprojectrefactoring.domain.TestDomain;

@Repository
public interface TestRepository extends JpaRepository<TestDomain, Integer>{

}
