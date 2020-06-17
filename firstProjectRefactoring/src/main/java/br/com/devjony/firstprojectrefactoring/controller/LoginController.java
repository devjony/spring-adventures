package br.com.devjony.firstprojectrefactoring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devjony.firstprojectrefactoring.domain.StudentDomain;
import br.com.devjony.firstprojectrefactoring.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("/login");
		mv.addObject("student", new StudentDomain());
		
		return mv;
	}
	
	@PostMapping("/validate")
	public String login(StudentDomain studentDomain) {
		return (loginService.login(studentDomain)) ? "redirect:index" : "redirect:/error";
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/index");
		
		return mv;
	}
	
	@GetMapping("/error")
	public ModelAndView error() {
		ModelAndView mv = new ModelAndView("/404");
		
		return mv;
	}
	
}
