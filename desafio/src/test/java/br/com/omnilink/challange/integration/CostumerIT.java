package br.com.omnilink.challange.integration;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequest;
import br.com.omnilink.challange.DTO.response.costumer.CostumerResponse;
import br.com.omnilink.challange.factory.CostumerFactory;
import br.com.omnilink.challange.model.Costumer;
import br.com.omnilink.challange.repository.costumer.CostumerRepository;
import br.com.omnilink.challange.security.model.User;
import br.com.omnilink.challange.security.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CostumerIT {

    @Autowired
    @Qualifier(value = "testRestTemplate")
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private UserRepository userRepository;

    private static final User USER = User.builder()
            .password("$2a$10$GiseHkdvwOFr7A9KRWbeiOmg/PYPhWVjdm42puLfOzR/gIAQrsAGy")
            .username("username")
            .build();

    @TestConfiguration
    @Lazy
    static class Config {
        @Bean(name = "testRestTemplate")
        public TestRestTemplate testRestTemplate(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port)
                    .basicAuthentication("username", "password");
            return new TestRestTemplate(restTemplateBuilder);
        }
    }

    @Test
    void findById_ReturnsCostumer_WhenSuccessful() {
        Costumer costumer = CostumerFactory.getCostumer();

        costumer.setId(null);

        userRepository.save(USER);

        costumerRepository.save(costumer);

        CostumerResponse forObject = testRestTemplate.getForObject("/costumer/finbyid/{id}", CostumerResponse.class, 1);

        Assertions.assertThat(forObject.id()).isNotNull().isEqualTo(1);
    }

    @Test
    void save_ReturnVoid_WhenUnccessful() {
        userRepository.save(USER);

        CostumerRequest request = CostumerFactory.getRequest();

        ResponseEntity<Void> entity = testRestTemplate.postForEntity("/costumer/save", request, Void.class);

        assertThat(entity).isNotNull();
    }

    @Test
    void delete_ReturnsVoid_WhenSuccessful() {
        Costumer costumer = CostumerFactory.getCostumer();

        costumer.setId(null);

        userRepository.save(USER);

        costumerRepository.save(costumer);

        CostumerResponse forObject = testRestTemplate.getForObject("/costumer/finbyid/{id}", CostumerResponse.class, 1);

        Assertions.assertThat(forObject.id()).isNotNull().isEqualTo(1);

        testRestTemplate.delete("/costumer/delete/1");

        CostumerResponse delete = testRestTemplate.getForObject("/costumer/finbyid/{id}", CostumerResponse.class, 1);

        Assertions.assertThat(delete.id()).isNull();
    }

}
