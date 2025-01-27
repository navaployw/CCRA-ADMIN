/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arg.ccra3.online.service;

import ch.qos.logback.classic.Logger;


import com.arg.ccra3.online.util.ResourceExceptionEntryPoint;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;

/**
 *
 * @author navaployw
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    private final Logger logger = (Logger) LoggerFactory.getLogger(ResourceServerConfiguration.class);
    @Autowired
    private ApiCtrlService apiCtrlService;
    private final String DEFAULT_PATH = "/home/kumpeep/Project/ccra-api-admin/ccra-api-admin/ccraapidb.properties";//fix to local path; 
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.exceptionHandling()
//            .authenticationEntryPoint(new ResourceExceptionEntryPoint())
//            .and()
        Properties aProp = new Properties();
        String loggerText  = "";
        String urlPath = apiCtrlService.getFileDBConfigPath();
//        String urlPath = DEFAULT_PATH;
        if(urlPath!=null){
            try(InputStream fileName = new FileInputStream(urlPath)) {
                aProp.load(fileName);
            }catch(FileNotFoundException ex){
                logger.error(ex.getMessage());
            }
            loggerText = String.format("URLPATH::: %s", urlPath);
            logger.info(loggerText);
            
            
        }

        logger.info(">>>>CorsConfiguration<<<<<<");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        logger.info("aProp.getProperty(\"api.allowheaders\": "+aProp.getProperty("api.allowheaders"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));//List.of(aProp.getProperty("api.allowheaders"))
        corsConfiguration.setAllowedOrigins(List.of(aProp.getProperty("api.alloworigin"),aProp.getProperty("api.allowadminorigin"),aProp.getProperty("api.allowauthorigin"))); //,"http://localhost:4200/","http://localhost:8082/","http://localhost:8080/""10.11.60.228:8082","10.11.60.228:8080","http://localhost:4200/","http://localhost:8082/","http://localhost:8080/"
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        loggerText = String.format("corsConfiguration: %s", corsConfiguration.getAllowedOrigins());
        logger.info(loggerText);
        http.cors().configurationSource(request -> corsConfiguration).and()
            .authorizeRequests()      
            .antMatchers("/ccraapiadmin/api/admin/**")
            .permitAll()    
            .antMatchers("/ccraapiadmin/get_ccra_report")
            .authenticated();

               
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // format message
        resources.authenticationEntryPoint(new ResourceExceptionEntryPoint());
        
    }
    
}
