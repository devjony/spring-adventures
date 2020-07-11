package br.com.devjony.firstprojectrefactoring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.devjony.firstprojectrefactoring.domain.UserDomain;

@Repository
public interface UserRepository extends CrudRepository<UserDomain, String>{

	UserDomain findByLogin(String login);
	
}
