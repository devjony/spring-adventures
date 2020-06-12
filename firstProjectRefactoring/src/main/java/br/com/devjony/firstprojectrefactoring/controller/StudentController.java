package br.com.devjony.firstprojectrefactoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devjony.firstprojectrefactoring.domain.StudentDomain;
import br.com.devjony.firstprojectrefactoring.service.StudentService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ModelAndView listAll(){
        logger.info("Request received on [GET][/student]");

        ModelAndView mv = new ModelAndView("student/list");
        mv.addObject("studentDomainList", studentService.findAll());

        logger.info("Returning view to the client");
        return mv;
    }
    
    @GetMapping("/create")
    public ModelAndView create() {
    	logger.info("Request received on [GET][/student/create]");
    	
    	ModelAndView mv = new ModelAndView("student/create");
    	mv.addObject("studentDomain", new StudentDomain());
    	
    	logger.info("Returning view to the client");
    	return mv;
    }
    
    @PostMapping("/save")
    public String save(StudentDomain studentDomain) {
    	logger.info("Request received on [GET][/student/save]");
    	
    	studentService.save(studentDomain);
    	
    	logger.info("Returning view to the client");
    	return "redirect:/student";
    }
    
    @GetMapping("/update/{id}")
    public ModelAndView updateStudent(@PathVariable("id") Integer id) throws ObjectNotFoundException {
    	logger.info("Request received on [GET][/student/update/" + id + "]" );
    	
    	ModelAndView mv = new ModelAndView("student/update");
    	mv.addObject("studentDomain", studentService.find(id));
    	
    	logger.info("Returning view to the client");
    	return mv;
    }
    
    @PostMapping("/update")
    public String update(StudentDomain studentDomain) throws ObjectNotFoundException {
    	logger.info("Request received on [POST][/student/update]");
    	studentService.update(studentDomain);
    	
    	return "redirect:/student";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
    	logger.info("Request received on [GET][student/delete/{id}]");
    	
    	studentService.delete(id);
    	
    	logger.info("Returning view to the client");
    	return "redirect:/student";
    }
}
