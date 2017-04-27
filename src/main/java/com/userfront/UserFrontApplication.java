package com.userfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class UserFrontApplication extends SpringBootServletInitializer {

	//if i am runing as jar,this method wil be used
	
	public static void main(String[] args) {
		SpringApplication.run(UserFrontApplication.class, args);
	}

	
	//used when run as war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		//return super.configure(builder);
		return builder.sources(UserFrontApplication.class);
	}
	
	
}
