package br.com.devjony.firstprojectrefactoring.service;

import br.com.devjony.firstprojectrefactoring.domain.StudentDomain;
import br.com.devjony.firstprojectrefactoring.repository.StudentRepository;
import javassist.tools.rmi.ObjectNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public StudentDomain find(Integer id) throws ObjectNotFoundException {
    	logger.info("Finding [StudentDomain] on Database");
    	
    	Optional<StudentDomain> student = studentRepository.findById(id);
    	
    	logger.info("Returning [StudentDomain]");
    	return student.orElseThrow(() -> new ObjectNotFoundException(
    			"not found, id: " + id + "Type: " + StudentDomain.class.getName()));
    }

    public List<StudentDomain> findAll() {
        logger.info("Finding all [StudentDomain] on Database");

        List<StudentDomain> allStudentDomain = this.studentRepository.findAll();

        logger.info("Returning [StudentDomain] List");
        return allStudentDomain;
    }

    public StudentDomain save(StudentDomain studentDomain) {
        logger.info("Saving [StudentDomain] on Database");

        StudentDomain studentDomainFromDb = studentRepository.save(studentDomain);

        logger.info("Returning [StudentDomain] saved on Database");
        return studentDomainFromDb;
    }
    
    public StudentDomain update(StudentDomain studentDomain) throws ObjectNotFoundException {
    	logger.info("Updating [StudentDomain] attributes");
    	
    	StudentDomain newStudentDomain = find(studentDomain.getId());
    	newStudentDomain.setName(studentDomain.getName());
    	newStudentDomain.setEmail(studentDomain.getEmail());
    	newStudentDomain.setPassword(studentDomain.getPassword());
    	
    	logger.info("Returning [StudentDomain] saved on Database");
    	return save(newStudentDomain);
    }
    
    public void delete(Integer id) {
    	logger.info("Deleting [StudentDomain], id: " + id);
    	studentRepository.deleteById(id);
    }
}
