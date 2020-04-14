package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.domain.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
