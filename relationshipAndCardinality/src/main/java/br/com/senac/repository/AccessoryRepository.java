package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.domain.Accessory;

public interface AccessoryRepository extends JpaRepository<Accessory, Integer> {

}
