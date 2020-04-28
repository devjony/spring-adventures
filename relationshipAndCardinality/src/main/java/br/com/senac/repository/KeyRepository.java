package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.domain.Key;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {

}
