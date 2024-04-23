package br.com.omnilink.desafio.controller;

import br.com.omnilink.desafio.model.Costumer;
import br.com.omnilink.desafio.repository.CostumerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/amizades")
public class CostumerController {

    @Autowired
    CostumerRepositoryImpl costumerRepository;

    @PostMapping("/ola")
    public void hello(@RequestBody Costumer costumer){
        costumerRepository.save(costumer);
    }
}
