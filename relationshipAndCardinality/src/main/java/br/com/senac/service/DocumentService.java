package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Document;
import br.com.senac.repository.DocumentRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class DocumentService {

	@Autowired
	DocumentRepository repoDocument;
	
	public Document search(Integer id) throws ObjectNotFoundException {
		Optional<Document> document = repoDocument.findById(id);
		
		return document.orElseThrow(() -> new ObjectNotFoundException(
				"não encontrado, id: " + id + "Tipo! " + Document.class.getName()));
	}
	
	public List<Document> searchAll() {
		return repoDocument.findAll();
	}
	
	public Document save(Document document) {
		return repoDocument.save(document);
	}
	
	public List<Document> saveAll(List<Document> documents) {
		return repoDocument.saveAll(documents);
	}
	
	public Document edit(Document document) throws ObjectNotFoundException {
		Document oldDocument = search(document.getId());
		oldDocument.setNumber(document.getNumber());
		
		return save(oldDocument);
	}
	
	public void delete(Integer id) {
		repoDocument.deleteById(id);
	}
}
