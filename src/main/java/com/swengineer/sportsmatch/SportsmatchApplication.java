package com.swengineer.sportsmatch;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class SportsmatchApplication {

	public static void main(String[] args) {
		setTimeZone();
		SpringApplication.run(SportsmatchApplication.class, args);
	}

	public static void setTimeZone(){
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
