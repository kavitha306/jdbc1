package com.kavitha;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@SpringBootApplication
public class JdbcSpringApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(JdbcSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Customer> customers = jdbcTemplate.query("select * from customer",
				new BeanPropertyRowMapper<Customer>(Customer.class));
		addCustomer("Kavi", "V", "kavitha@gmail.com", "9986921221");
		customers = jdbcTemplate.query("select * from customer",
				new BeanPropertyRowMapper<Customer>(Customer.class));
		System.out.println(customers);
	}
	
	String INSERT_MESSAGE_SQL = "INSERT INTO customer (first_name," +
            "last_name," +
            "email," +
            " mobile)" +
            "VALUES (?, ?, ?, ?)";
	     
	public int addCustomer(String firstName,String lastName,String email,String mobile) {    
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	 
	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(INSERT_MESSAGE_SQL,Statement.RETURN_GENERATED_KEYS);
	          ps.setString(1, firstName);
	          ps.setString(2, lastName);
	          ps.setString(3, email);
	          ps.setString(4, mobile);
	          return ps;
	        }, keyHolder);
	 
	        return keyHolder.getKey().intValue();
	    }
	

}
