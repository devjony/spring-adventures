package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.domain.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{

}
