package br.com.senac.inicialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.domain.Car;
import br.com.senac.domain.Document;
import br.com.senac.service.CarService;
import br.com.senac.service.DocumentService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	CarService carService;
	
	@Autowired
	DocumentService documentService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Car car1 = new Car();
		car1.setPlate("RPC698");
		carService.save(car1);
		
		Car car2 = new Car();
		car2.setPlate("PRT995");
		carService.save(car2);
		
		Document doc1 = new Document();
		doc1.setNumber("12759685985");
		documentService.save(doc1);
		
		Document doc2 = new Document();
		doc2.setNumber("85478965896");
		documentService.save(doc2);
	}
	
	
}
