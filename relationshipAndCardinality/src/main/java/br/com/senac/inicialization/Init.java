package br.com.senac.inicialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.domain.Accessory;
import br.com.senac.domain.Document;
import br.com.senac.domain.Key;
import br.com.senac.domain.Manufacturer;
import br.com.senac.service.AccessoryService;
import br.com.senac.service.CarService;
import br.com.senac.service.DocumentService;
import br.com.senac.service.KeyService;
import br.com.senac.service.ManufacturerService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	CarService carService;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	KeyService keyService;
	
	@Autowired
	ManufacturerService manufacturerService;
	
	@Autowired
	AccessoryService accessoryService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
//		Car car1 = new Car();
//		car1.setModel("Toyota Corolla");
//		carService.save(car1);
//		
//		Car car2 = new Car();
//		car2.setModel("New Civic");
//		carService.save(car2);
		
		Document doc1 = new Document();
		doc1.setNumber("12759685985");
		documentService.save(doc1);
		
		Document doc2 = new Document();
		doc2.setNumber("85478965896");
		documentService.save(doc2);
		
		Key key1 = new Key();
		key1.setCode("123454");
		keyService.save(key1);
		
		Key key2 = new Key();
		key2.setCode("8889745");
		keyService.save(key2);
		
		Manufacturer manu1 = new Manufacturer();
		manu1.setName("Teste");
		manufacturerService.save(manu1);
		
		Manufacturer manu2 = new Manufacturer();
		manu2.setName("Ford");
		manufacturerService.save(manu2);
		
		Accessory acc1 = new Accessory();
		acc1.setName("Virdros");
		accessoryService.save(acc1);
		
		Accessory acc2 = new Accessory();
		acc2.setName("Trava");
		accessoryService.save(acc2);
		
		Accessory acc3 = new Accessory();
		acc3.setName("Liga leve");
		accessoryService.save(acc3);
	}
}
