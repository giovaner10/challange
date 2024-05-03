package br.com.omnilink.challange.controller;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequest;
import br.com.omnilink.challange.DTO.response.costumer.CostumerResponse;
import br.com.omnilink.challange.service.costumer.ICostumerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    @Autowired
    ICostumerService costumerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody CostumerRequest request) {
        costumerService.save(request);
    }

    @GetMapping("/findall")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findAll")
    public List<CostumerResponse> findAll() {
        return costumerService.findAll();
    }

    @GetMapping("/finbyid/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findById")
    public CostumerResponse findById(@PathVariable Integer id) {
        return costumerService.findById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id, @Valid @RequestBody CostumerRequest request) {
        costumerService.update(request, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        costumerService.delete(id);
    }
}
