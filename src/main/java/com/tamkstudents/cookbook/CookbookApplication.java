package com.tamkstudents.cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//mikäli et ole asettanut vielä tietokantaa niin poista kommentit exlude osiosta.
@SpringBootApplication(/*exclude =  {DataSourceAutoConfiguration.class}*/)
public class CookbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookbookApplication.class, args);
	}

}
