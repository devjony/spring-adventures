package br.com.devjony.firstprojectrefactoring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.devjony.firstprojectrefactoring.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
	
}
