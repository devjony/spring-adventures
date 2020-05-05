package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Accessory;
import br.com.senac.service.AccessoryService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/accessory")
public class AccessoryController {
	
	@Autowired
	AccessoryService service;
	
	@GetMapping("/list")
	public ModelAndView listAccessories() {
		ModelAndView mv = new ModelAndView("accessory/listAccessory");
		mv.addObject("accessories", service.searchAll());
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertAccessory() {
		ModelAndView mv = new ModelAndView("accessory/createAccessory");
		mv.addObject("accessory", new Accessory());
		return mv;
	}
	
	@PostMapping("/save")
	public String saveAccessory(Accessory accessory) {
		service.save(accessory);
		
		return "redirect:/accessory/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAccessory(@PathVariable("id") Integer id) {
		service.delete(id);
		return "redirect:/accessory/list";
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView updateAccessory(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("accessory/updateAccessory");
		mv.addObject(service.search(id));
		return mv;
	}
	
	@PostMapping("/update")
	public String update(Accessory accessory) throws ObjectNotFoundException {
		service.edit(accessory);
		
		return "redirect:/accessory/list";
	}
}
