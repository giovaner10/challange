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
///	private static final Logger logger = LoggerFactory.getLogger(DesafioApplication.class);

	public static void main(String[] args) {
	//	logger.info("Iniciando a api!");
		SpringApplication.run(ChallangeApplication.class, args);
	//	logger.info("API iniciada e pronta para receber requisições!");

	}

	@Bean
	ApplicationRunner runner(PasswordEncoder passwordEncoder) {
		return args -> System.out.println(passwordEncoder.encode("password"));
	}


}
