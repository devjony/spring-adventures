package br.com.devjony.firstprojectrefactoring.service;

import br.com.devjony.firstprojectrefactoring.domain.QuestionDomain;
import br.com.devjony.firstprojectrefactoring.repository.QuestionRepository;
import javassist.tools.rmi.ObjectNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    
    public QuestionDomain find(Integer id) throws ObjectNotFoundException {
    	logger.info("Finding [QuestionDomain] on Database");
    	
    	Optional<QuestionDomain> question = questionRepository.findById(id);
    	
    	logger.info("Returning [QuestionDomain]");
    	return question.orElseThrow(() -> new ObjectNotFoundException(
    			"not found, id: " + id + "Type: " + QuestionDomain.class.getName()));
    }

    public List<QuestionDomain> findAll() {
        logger.info("Finding all [QuestionDomain] on Database");

        List<QuestionDomain> allQuestionDomain = questionRepository.findAll();

        logger.info("Returning [QuestionDomain] List");
        return allQuestionDomain;
    }

    public QuestionDomain save(QuestionDomain questionDomain) {
        logger.info("Saving [QuestionDomain] on Database");

        QuestionDomain QuestionDomainFromDb = questionRepository.save(questionDomain);

        logger.info("Returning [QuestionDomain] saved on Database");
        return QuestionDomainFromDb;
    }
    
    public QuestionDomain update(QuestionDomain questionDomain) throws ObjectNotFoundException {
    	logger.info("Updating [QuestionDomain] attributes");
    	
    	QuestionDomain newQuestionDomain = find(questionDomain.getId());
    	newQuestionDomain.setDescription(questionDomain.getDescription());
    	
    	logger.info("Returning [QuestionDomain] saved on Database");
    	return save(newQuestionDomain);
    }
    
    public void delete(Integer id) {
    	logger.info("Deleting [QuestionDomain], id: " + id);
    	questionRepository.deleteById(id);
    }
}
