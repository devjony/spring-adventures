package br.com.devjony.firstprojectrefactoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devjony.firstprojectrefactoring.domain.ExamDomain;
import br.com.devjony.firstprojectrefactoring.service.TestService;
import br.com.devjony.firstprojectrefactoring.service.ExamService;
import br.com.devjony.firstprojectrefactoring.service.StudentService;

@Controller
@RequestMapping("/exam")
public class ExamController {
	
	Logger logger = LoggerFactory.getLogger(ExamController.class);

	@Autowired
	StudentService studentService;
	
	@Autowired
	TestService courseService;
	
	@Autowired
	ExamService examService;
	
	@GetMapping("/add") 
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("");
		mv.addObject("students", studentService.findAll());
		mv.addObject("courses", courseService.findAll());
		mv.addObject("exam", new ExamDomain());
		
		return mv;
	}
}
