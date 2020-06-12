package br.com.devjony.firstprojectrefactoring.service;

import br.com.devjony.firstprojectrefactoring.domain.TestDomain;
import br.com.devjony.firstprojectrefactoring.repository.TestRepository;
import javassist.tools.rmi.ObjectNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }
    
    public TestDomain find(Integer id) throws ObjectNotFoundException {
    	logger.info("Finding [TestDomain] on Database");
    	
    	Optional<TestDomain> test = testRepository.findById(id);
    	
    	logger.info("Returning [TestDomain]");
    	return test.orElseThrow(() -> new ObjectNotFoundException(
    			"not found, id: " + id + "Type: " + TestDomain.class.getName()));
    }

    public List<TestDomain> findAll() {
        logger.info("Finding all [TestDomain] on Database");

        List<TestDomain> allTestDomain = testRepository.findAll();

        logger.info("Returning [TestDomain] List");
        return allTestDomain;
    }

    public TestDomain save(TestDomain testDomain) {
        logger.info("Saving [TestDomain] on Database");

        TestDomain testDomainFromDb = testRepository.save(testDomain);

        logger.info("Returning [TestDomain] saved on Database");
        return testDomainFromDb;
    }
    
    public TestDomain update(TestDomain testDomain) throws ObjectNotFoundException {
    	logger.info("Updating [TestDomain] attributes");
    	
    	TestDomain newTestDomain = find(testDomain.getId());
    	newTestDomain.setName(testDomain.getName());
    	newTestDomain.setQuestions(testDomain.getQuestions());
    	
    	logger.info("Returning [TestDomain] saved on Database");
    	return save(newTestDomain);
    }
    
    public void delete(Integer id) {
    	logger.info("Deleting [TestDomain], id: " + id);
    	testRepository.deleteById(id);
    }
}
