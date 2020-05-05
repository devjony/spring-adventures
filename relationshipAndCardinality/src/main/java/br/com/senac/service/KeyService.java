package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Key;
import br.com.senac.repository.KeyRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class KeyService {

	@Autowired
	KeyRepository repoKey;
	
	public Key search(Integer id) throws ObjectNotFoundException {
		Optional<Key> key = repoKey.findById(id);
		
		return key.orElseThrow(() -> new ObjectNotFoundException(
				"not find, id: " + id + "Type: " + Key.class.getName()));
	}
	
	public List<Key> searchAll(){
		return repoKey.findAll();
	}
	
	public Key save(Key Key) {
		return repoKey.save(Key);
	}
	
	public List<Key> saveAll(List<Key> keys) {
		return repoKey.saveAll(keys);
	}
	
	public Key edit(Key key) throws ObjectNotFoundException {
		Key oldKey = search(key.getId());
		oldKey.setCode(key.getCode());
		
		return save(oldKey);
	}
	
	public void delete(Integer id) {
		repoKey.deleteById(id);
	}
}
