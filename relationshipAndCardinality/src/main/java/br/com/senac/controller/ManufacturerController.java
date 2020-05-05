package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Manufacturer;
import br.com.senac.service.ManufacturerService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {

	@Autowired
	ManufacturerService service;
	
	@GetMapping("/list")
	public ModelAndView listManufacturers() {
		ModelAndView mv = new ModelAndView("manufacturer/listManufacturer");
		mv.addObject("manufacturers", service.searchAll());
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertManufacturer() {
		ModelAndView mv = new ModelAndView("manufacturer/createManufacturer");
		mv.addObject("manufacturer", new Manufacturer());
		
		return mv;
	}
	
	@PostMapping("/save")
	public String saveManufacturer(Manufacturer manufacturer) {
		service.save(manufacturer);
		
		return "redirect:/manufacturer/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteManufacturer(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		service.delete(id);
		
		return "redirect:/manufacturer/list";
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView updateManufacturer(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("manufacturer/updateManufacturer");
		mv.addObject("manufacturer", service.search(id));
		
		return mv;
	}
	
	@PostMapping("/update")
	public String update(Manufacturer manufacturer) throws ObjectNotFoundException {
		service.edit(manufacturer);
		
		return "redirect:/list";
	}
}
