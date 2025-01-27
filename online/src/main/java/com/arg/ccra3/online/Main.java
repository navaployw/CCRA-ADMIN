package com.arg.ccra3.online;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableResourceServer
@EnableAutoConfiguration
@SpringBootApplication
@EntityScan("com.arg.ccra3.model")
@EnableJpaRepositories("com.arg.ccra3.dao.repo")
@EnableJpaAuditing//https://stackoverflow.com/questions/40370709/createddate-annotation-does-not-work-with-mysql
public class Main implements ErrorController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            SpringApplication.run(Main.class, args);
        } catch (Exception e) {
//            e.printStackTrace();  //use only on test environment
            logger.error(e.getMessage());
        }
    }
    
    private final String PATH = "/error";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public String error() {
        return "forward:/index.html";
    }

}
