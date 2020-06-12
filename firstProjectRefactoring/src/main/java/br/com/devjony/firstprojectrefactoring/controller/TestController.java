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

import br.com.devjony.firstprojectrefactoring.domain.TestDomain;
import br.com.devjony.firstprojectrefactoring.service.QuestionService;
import br.com.devjony.firstprojectrefactoring.service.TestService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;
    
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ModelAndView listAll(){
        logger.info("Request received on [GET][/test]");

        ModelAndView mv = new ModelAndView("/test/list");
        mv.addObject("testDomainList", testService.findAll());

        logger.info("Returning view to the client");
        return mv;
    }
    
    @GetMapping("/create")
    public ModelAndView create() {
    	logger.info("Request received on [GET][/test/create]");
    	
    	ModelAndView mv = new ModelAndView("test/create");
    	mv.addObject("testDomain", new TestDomain());
    	mv.addObject("questionDomainList", questionService.findAll());
    	
    	logger.info("Returning view to the client");
    	return mv;
    }
    
    @PostMapping("/save")
    public String save(TestDomain testDomain) {
    	logger.info("Request received on [GET][/test/save]");
    	
    	testService.save(testDomain);
    	
    	logger.info("Returning view to the client");
    	return "redirect:/test#list";
    }
    
    @GetMapping("/update/{id}")
    public ModelAndView updateTest(@PathVariable("id") Integer id) throws ObjectNotFoundException {
    	logger.info("Request received on [GET][/test/update/" + id + "]" );
    	
    	ModelAndView mv = new ModelAndView("test/update");
    	mv.addObject("testDomain", testService.find(id));
    	mv.addObject("questions", questionService.findAll());
    	
    	logger.info("Returning view to the client");
    	return mv;
    }
    
    @PostMapping("/update")
    public String update(TestDomain testDomain) throws ObjectNotFoundException {
    	logger.info("Request received on [POST][/test/update]");
    	
    	testService.update(testDomain);
    	
    	return "redirect:/test#list";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
    	logger.info("Request received on [GET][test/delete/{id}]");
    	
    	testService.delete(id);
    	
    	logger.info("Returning view to the client");
    	return "redirect:/test#list";
    }
}
