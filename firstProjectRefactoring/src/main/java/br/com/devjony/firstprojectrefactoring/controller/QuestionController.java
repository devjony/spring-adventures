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

import br.com.devjony.firstprojectrefactoring.domain.QuestionDomain;
import br.com.devjony.firstprojectrefactoring.service.QuestionService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/question")
public class QuestionController {

    Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ModelAndView listAll(){
        logger.info("Request received on [GET][/question]");

        ModelAndView mv = new ModelAndView("question/list");
        mv.addObject("questionDomainList", questionService.findAll());

        logger.info("Returning view to the client");
        return mv;
    }
    
    @GetMapping("/create")
    public ModelAndView create() {
    	logger.info("Request received on [GET][/question/create]");
    	
    	ModelAndView mv = new ModelAndView("question/create");
    	mv.addObject("questionDomain", new QuestionDomain());
    	
    	logger.info("Returning view to the client");
    	return mv;
    }
    
    @PostMapping("/save")
    public String save(QuestionDomain questionDomain) {
    	logger.info("Request received on [GET][/question/save]");
    	
    	questionService.save(questionDomain);
    	
    	logger.info("Returning view to the client");
    	return "redirect:/question";
    }
    
    @GetMapping("/update/{id}")
    public ModelAndView updateStudent(@PathVariable("id") Integer id) throws ObjectNotFoundException {
    	logger.info("Request received on [GET][/question/update/" + id + "]" );
    	
    	ModelAndView mv = new ModelAndView("question/update");
    	mv.addObject("questionDomain", questionService.find(id));
    	
    	logger.info("Returning view to the client");
    	return mv;
    }
    
    @PostMapping("/update")
    public String update(QuestionDomain questionDomain) throws ObjectNotFoundException {
    	logger.info("Request received on [POST][/question/update]");
    	questionService.update(questionDomain);
    	
    	return "redirect:/question";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
    	logger.info("Request received on [GET][question/delete/{id}]");
    	
    	questionService.delete(id);
    	
    	logger.info("Returning view to the client");
    	return "redirect:/question#list";
    }
}
