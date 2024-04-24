package br.com.omnilink.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
@EnableCaching
public class DesafioApplication {
///	private static final Logger logger = LoggerFactory.getLogger(DesafioApplication.class);

	public static void main(String[] args) {
	//	logger.info("Iniciando a api!");
		SpringApplication.run(DesafioApplication.class, args);
	//	logger.info("API iniciada e pronta para receber requisições!");

	}

}
