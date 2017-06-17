package com.main;

import com.model.Category;
import com.model.User;
import com.service.interfaces.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;


@ComponentScan(value = "com")
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ImportResource("classpath:servlet-context.xml")
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(Main.class, args);
    }
}
