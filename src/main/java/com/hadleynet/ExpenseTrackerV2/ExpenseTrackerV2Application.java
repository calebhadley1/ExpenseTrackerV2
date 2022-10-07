package com.hadleynet.ExpenseTrackerV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {
//		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
//})
@SpringBootApplication
public class ExpenseTrackerV2Application {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerV2Application.class, args);
	}

}
