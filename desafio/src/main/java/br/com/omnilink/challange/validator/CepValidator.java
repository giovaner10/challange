package br.com.omnilink.challange.validator;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.challange.exception.ObjectNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;

public class CepValidator {

    public static void validate(VehicleRequestCreat request) throws JsonProcessingException {
        String cepResponse = WebClient
                .create("https://viacep.com.br/ws")
                .get()
                .uri("/{cep}/json", request.cep())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(cepResponse);

        String uf = jsonNode.get("uf").asText();
        String localidade = jsonNode.get("localidade").asText();

        if (!(uf.equals(request.state()) && localidade.equals(request.city()))) {
            throw new ObjectNotFoundException("city and/or uf not valids");
        }
    }
}
