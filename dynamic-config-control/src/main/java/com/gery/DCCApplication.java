package com.gery;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
public class DCCApplication {

    public static void main(String[] args){
        SpringApplication.run(DCCApplication.class);
    }

}
