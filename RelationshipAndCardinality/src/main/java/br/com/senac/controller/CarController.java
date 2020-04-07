package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Car;
import br.com.senac.service.CarService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	CarService service;
	
	@GetMapping("/list")
	public ModelAndView listCars() {
		ModelAndView mv = new ModelAndView("car/listCar");
		mv.addObject("cars", service.searchAll());
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertCar() {
		ModelAndView mv = new ModelAndView("car/createCar");
		mv.addObject("car", new Car());
		
		return mv;
	}
	
	@PostMapping("/save")
	public String saveCar(Car car) {
		service.save(car);
		
		return "redirect:/car/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		service.delete(id);
		
		return "redirect:/car/list";
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView updateCar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("car/updateCar");
		mv.addObject("car", service.search(id));
		
		return mv;
	}
	
	@PostMapping("/update")
	public String update(Car car) throws ObjectNotFoundException {
		service.edit(car);
		
		return "redirect:/list";
	}
}
