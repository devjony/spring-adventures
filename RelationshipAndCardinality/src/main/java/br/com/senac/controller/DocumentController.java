package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Document;
import br.com.senac.service.DocumentService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/document")
public class DocumentController {
	
	@Autowired
	DocumentService service;
	
	@GetMapping("/list")
	public ModelAndView listDocuments() {
		ModelAndView mv = new ModelAndView("document/listDocument");
		mv.addObject("documents", service.searchAll());
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertDocument() {
		ModelAndView mv = new ModelAndView("document/createDocument");
		mv.addObject("document", new Document());
		
		return mv;
	}
	
	@PostMapping("/save")
	public String saveDocument(Document document) {
		service.save(document);
		
		return "redirect:/document/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDocument(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		service.delete(id);
		
		return "redirect:/document/list";
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView updateDocument(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("document/updateDocument");
		mv.addObject("document", service.search(id));
		
		return mv;
	}
	
	@PostMapping("/update")
	public String update(Document document) throws ObjectNotFoundException {
		service.edit(document);
		
		return "redirect:/document/list";
	}
}
