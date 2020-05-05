package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Manufacturer;
import br.com.senac.repository.ManufacturerRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ManufacturerService {

	@Autowired
	ManufacturerRepository repoManufacturer;
	
	public Manufacturer search(Integer id) throws ObjectNotFoundException {
		Optional<Manufacturer> manufacturer = repoManufacturer.findById(id);
		
		return manufacturer.orElseThrow(() -> new ObjectNotFoundException(
				"not find, id: " + id + "Type: " + Manufacturer.class.getName()));
	}
	
	public List<Manufacturer> searchAll(){
		return repoManufacturer.findAll();
	}
	
	public Manufacturer save(Manufacturer manufacturer) {
		return repoManufacturer.save(manufacturer);
	}
	
	public List<Manufacturer> saveAll(List<Manufacturer> manufacturers) {
		return repoManufacturer.saveAll(manufacturers);
	}
	
	public Manufacturer edit(Manufacturer manufacturer) throws ObjectNotFoundException {
		Manufacturer oldManufacturer = search(manufacturer.getId());
		oldManufacturer.setName(manufacturer.getName());
		
		return save(oldManufacturer);
	}
	
	public void delete(Integer id) {
		repoManufacturer.deleteById(id);
	}
}
