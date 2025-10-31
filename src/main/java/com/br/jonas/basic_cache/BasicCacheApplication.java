package com.br.jonas.basic_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BasicCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicCacheApplication.class, args);
	}

}
