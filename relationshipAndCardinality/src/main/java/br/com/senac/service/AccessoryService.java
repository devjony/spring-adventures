package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Accessory;
import br.com.senac.repository.AccessoryRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AccessoryService {

	@Autowired
	AccessoryRepository repoAccessory;
	
	public Accessory search(Integer id) throws ObjectNotFoundException {
		Optional<Accessory> accessory = repoAccessory.findById(id);
		
		return accessory.orElseThrow(() -> new ObjectNotFoundException(
				" not find, id: " + id + ", Type: " + Accessory.class.getName()));
	}
	
	public List<Accessory> searchAll() {
		return repoAccessory.findAll();
	}
	
	public Accessory save(Accessory accessory) {
		return repoAccessory.save(accessory);
	}
	
	public List<Accessory> saveAll(List<Accessory> accessories) {
		return repoAccessory.saveAll(accessories);
	}
	
	public Accessory edit(Accessory accessory) throws ObjectNotFoundException {
		Accessory oldAccessory = search(accessory.getId());
		
		oldAccessory.setName(accessory.getName());
		
		return save(oldAccessory);
	}
	
	public void delete(Integer id) {
		repoAccessory.deleteById(id);
	}
}
