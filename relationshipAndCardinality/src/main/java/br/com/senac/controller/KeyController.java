package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Key;
import br.com.senac.service.KeyService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/key")
public class KeyController {

	@Autowired
	KeyService service;
	
	@GetMapping("/list")
	public ModelAndView listKeys() {
		ModelAndView mv = new ModelAndView("key/listKey");
		mv.addObject("keys", service.searchAll());
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertKey() {
		ModelAndView mv = new ModelAndView("key/createKey");
		mv.addObject("key", new Key());
		
		return mv;
	}
	
	@PostMapping("/save")
	public String saveKey(Key key) {
		service.save(key);
		
		return "redirect:/key/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteKey(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		service.delete(id);
		
		return "redirect:/key/list";
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView updateKey(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("key/updateKey");
		mv.addObject("key", service.search(id));
		
		return mv;
	}
	
	@PostMapping("/update")
	public String update(Key key) throws ObjectNotFoundException {
		service.edit(key);
		
		return "redirect:/key/list";
	}
}
