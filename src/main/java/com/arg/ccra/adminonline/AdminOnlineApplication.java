package com.arg.ccra.adminonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @Controller
@SpringBootApplication
public class AdminOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminOnlineApplication.class, args);
	}

	// private final String PATH = "/error";

    // @RequestMapping(value = PATH, method = RequestMethod.GET)
    // public String error() {
    //     return "forward:/index.html";
    // }

}
