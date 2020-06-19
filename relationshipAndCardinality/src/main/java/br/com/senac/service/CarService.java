package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Car;
import br.com.senac.repository.CarRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarService {

	@Autowired
	CarRepository repoCar;
	
	public Car search(Integer id) throws ObjectNotFoundException {
		Optional<Car> car = repoCar.findById(id);
		
		return car.orElseThrow(() -> new ObjectNotFoundException(
				"not find, id: " + id + "Type: " + Car.class.getName()));
	}
	
	public List<Car> searchAll(){
		return repoCar.findAll();
	}
	
	public Car save(Car car) {
		return repoCar.save(car);
	}
	
	public List<Car> saveAll(List<Car> cars) {
		return repoCar.saveAll(cars);
	}
	
	public Car edit(Car car) throws ObjectNotFoundException {
		Car oldCar = search(car.getId());
		oldCar.setModel(car.getModel());
		oldCar.setDocument(car.getDocument());
		oldCar.setKey(car.getKey());
		oldCar.setManufacturer(car.getManufacturer());
		oldCar.setAccessories(car.getAccessories());
		
		return save(oldCar);
	}
	
	public void delete(Integer id) {
		repoCar.deleteById(id);
	}
}
