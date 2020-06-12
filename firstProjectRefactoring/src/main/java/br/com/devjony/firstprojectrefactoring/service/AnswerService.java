package br.com.devjony.firstprojectrefactoring.service;

import br.com.devjony.firstprojectrefactoring.domain.AnswerDomain;
import br.com.devjony.firstprojectrefactoring.repository.AnswerRepository;
import javassist.tools.rmi.ObjectNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    Logger logger = LoggerFactory.getLogger(AnswerService.class);

    @Autowired
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    
    public AnswerDomain find(Integer id) throws ObjectNotFoundException {
    	logger.info("Finding [AnswerDomain] on Database");
    	
    	Optional<AnswerDomain> answer = answerRepository.findById(id);
    	
    	logger.info("Returning [AnswerDomain]");
    	return answer.orElseThrow(() -> new ObjectNotFoundException(
    			"not found, id: " + id + "Type: " + AnswerDomain.class.getName()));
    }

    public List<AnswerDomain> findAll() {
        logger.info("Finding all [AnswerDomain] on Database");

        List<AnswerDomain> allAnswerDomain = answerRepository.findAll();

        logger.info("Returning [AnswerDomain] List");
        return allAnswerDomain;
    }

    public AnswerDomain save(AnswerDomain answerDomain) {
        logger.info("Saving [AnswerDomain] on Database");

        AnswerDomain answerDomainFromDb = answerRepository.save(answerDomain);

        logger.info("Returning [AnswerDomain] saved on Database");
        return answerDomainFromDb;
    }
    
    public AnswerDomain update(AnswerDomain answerDomain) throws ObjectNotFoundException {
    	logger.info("Updating [AnswerDomain] attributes");
    	
    	AnswerDomain newAnswerDomain = find(answerDomain.getId());
    	newAnswerDomain.setDescription(answerDomain.getDescription());
    	
    	logger.info("Returning [AnswerDomain] saved on Database");
    	return save(newAnswerDomain);
    }
    
    public void delete(Integer id) {
    	logger.info("Deleting [AnswerDomain], id: " + id);
    	answerRepository.deleteById(id);
    }
}
