package com.kavitha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaSpringApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = new Customer();
		customer.setEmail("email1");
		customer.setMobile("mobile1");
		customer.setFirstName("firstName1");
		customer.setLastName("lastName1");
		
		customer = customerRepository.save(customer);
		System.out.println(customer.getId());
		
		Iterable<Customer> customers = customerRepository.findAll();
		System.out.println(customers);
		
		customers = customerRepository.findByLastName("S");
		System.out.println(customers);
		
		customers = customerRepository.findByFirstName("Prathiksha");
		System.out.println(customers);
		
		System.out.println("No of Customers:"+ customerRepository.count());
		
	}

}

