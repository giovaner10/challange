package br.com.omnilink.challange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class ChallangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallangeApplication.class, args);
	}

}
