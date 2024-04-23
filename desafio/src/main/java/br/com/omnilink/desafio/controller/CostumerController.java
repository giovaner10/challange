package br.com.omnilink.desafio.controller;

import br.com.omnilink.desafio.model.Costumer;
import br.com.omnilink.desafio.repository.costumer.CostumerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    @Autowired
    CostumerRepositoryImpl costumerRepository;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Costumer costumer) {
        costumerRepository.save(costumer);
    }

    @GetMapping("/findall")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findAll")
    public List<Costumer> findAll() {
        return costumerRepository.findAll();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id, @RequestBody Costumer costumer) {
        costumer.setId(id);
        costumerRepository.update(costumer);
    }

    @GetMapping("/finbyid/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findById")
    public Costumer findById(@PathVariable Integer id) {
        return costumerRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        costumerRepository.delete(costumerRepository.findById(id));
    }
}
