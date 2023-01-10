package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {

		SpringApplication.run(StudentApplication.class, args);
		var lol = "lol";
		var smiley = lol.toUpperCase() == lol;
		var smirk = lol.toUpperCase() == lol.toUpperCase();
		System.out.println(smiley);
		System.out.println(smirk);
	}


}
