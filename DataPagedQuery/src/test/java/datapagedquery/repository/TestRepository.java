package datapagedquery.repository;


import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import datapagedquery.domain.Customer;



public class TestRepository {

	static Logger log = Logger.getLogger( 
			TestRepository.class.getName()); 
	static CustomerRepository repository;

	@BeforeClass
	public static void init() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:applicationContext.xml");
		ctx.refresh();
		
		repository = ctx.getBean("customerRepository", CustomerRepository.class);
	}
	
	@Test
	public void testFindAllPaged() {
		PageRequest pr = new PageRequest(1,10);
		Page<Customer> page = repository.findAll(pr);
		
		for (Customer c : page.getContent()) {
			log.info(c);
		}		
	}
	
	@Test
	public void testFindbyNamePatternPaged() {
		PageRequest pr = new PageRequest(1,10);
		String keyword = "%customer%";
		Page<Customer> page = repository.findByNamePattern(keyword, pr);
		
		for (Customer c : page.getContent()) {
			log.info(c);
		}		
	}
	
}
