package br.com.devjony.firstprojectrefactoring.init;

import br.com.devjony.firstprojectrefactoring.domain.QuestionDomain;
import br.com.devjony.firstprojectrefactoring.domain.StudentDomain;
import br.com.devjony.firstprojectrefactoring.service.QuestionService;
import br.com.devjony.firstprojectrefactoring.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger = LoggerFactory.getLogger(Init.class);

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private QuestionService questionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Starting saving mock values on database");

        StudentDomain student = new StudentDomain();
        student.setName("Student Name 1");
        studentService.save(student);
        
        StudentDomain student2 = new StudentDomain();
        student2.setName("Student Name 2");
        studentService.save(student2);
        
        StudentDomain student3 = new StudentDomain();
        student3.setName("Student Name 3");
        studentService.save(student3);
        
        StudentDomain student4 = new StudentDomain();
        student4.setName("Student Name 4");
        studentService.save(student4);
        
        QuestionDomain question = new QuestionDomain();
        question.setDescription("Pergunta de número 1");
        questionService.save(question);
        
        QuestionDomain question2 = new QuestionDomain();
        question2.setDescription("Pergunta de número 2");
        questionService.save(question2);
        
        QuestionDomain question3 = new QuestionDomain();
        question3.setDescription("Pergunta de número 3");
        questionService.save(question3);
        
        QuestionDomain question4 = new QuestionDomain();
        question4.setDescription("Pergunta de número 4");
        questionService.save(question4);
        
        logger.info("Finishing saving mock values on database");
    }

}
