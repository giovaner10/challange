package br.com.omnilink.desafio.controller;

import br.com.omnilink.desafio.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.desafio.DTO.response.CostumerResponse;
import br.com.omnilink.desafio.model.Costumer;
import br.com.omnilink.desafio.service.CostumerService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costumer")
public class CostumerController {
    @Autowired
    CostumerService costumerRepository;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody CostumerRequestCreat request) throws BadRequestException {
        costumerRepository.save(request);
    }

    @GetMapping("/findall")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findAll")
    public List<CostumerResponse> findAll() {
        System.out.println("testando cache");
        //logger.info("Listando tudo!");
        return costumerRepository.findAll();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id, @Valid @RequestBody CostumerRequestCreat request) throws BadRequestException {

        costumerRepository.update(request, id);
    }

    @GetMapping("/finbyid/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findById")
    public Costumer findById(@PathVariable Integer id) throws BadRequestException {
        return costumerRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) throws BadRequestException {
        costumerRepository.delete(id);
    }
}
