package com.mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebsocktestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocktestApplication.class, args);
	}
}
