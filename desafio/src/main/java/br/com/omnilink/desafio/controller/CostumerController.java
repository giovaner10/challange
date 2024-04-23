package br.com.omnilink.desafio.controller;

import br.com.omnilink.desafio.model.Costumer;
import br.com.omnilink.desafio.repository.costumer.CostumerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    @Autowired
    CostumerRepositoryImpl costumerRepository;

    @PostMapping("/")
    public void save(@RequestBody Costumer costumer){
        costumerRepository.save(costumer);
    }
}
