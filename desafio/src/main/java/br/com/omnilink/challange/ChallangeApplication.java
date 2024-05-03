package br.com.omnilink.challange;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableCaching
public class ChallangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallangeApplication.class, args);
	}

}
