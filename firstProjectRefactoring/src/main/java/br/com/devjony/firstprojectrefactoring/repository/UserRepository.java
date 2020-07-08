package br.com.devjony.firstprojectrefactoring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.devjony.firstprojectrefactoring.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

	User findByUserLogin(String userLogin);
}
