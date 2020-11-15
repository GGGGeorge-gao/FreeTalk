package com.cygao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cygao.dao")
public class FreeTalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreeTalkApplication.class, args);
	}

}
