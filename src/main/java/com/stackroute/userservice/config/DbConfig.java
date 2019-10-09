//package com.stackroute.userservice.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//@ConfigurationProperties("spring.datasource")
//@SuppressWarnings("unused")
//public class DbConfig {
//    private String driverClass;
//    private String url;
//    private String username;
//    private String password;
//
//    @Profile("dev")
//    public String devDatabaseConnection(){
//        System.out.println(driverClass);
//        return "Dev Database";
//    }
//    @Profile("prod")
//    public String prodDatabaseConnection(){
//        System.out.println(driverClass);
//        return "prod Database";
//    }
//}
