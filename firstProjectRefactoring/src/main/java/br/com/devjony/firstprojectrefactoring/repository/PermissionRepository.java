package br.com.devjony.firstprojectrefactoring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.devjony.firstprojectrefactoring.domain.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, String> {

}
