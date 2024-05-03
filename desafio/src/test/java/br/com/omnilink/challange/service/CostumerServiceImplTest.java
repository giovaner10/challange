package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequest;
import br.com.omnilink.challange.DTO.response.costumer.CostumerResponse;
import br.com.omnilink.challange.exception.model.BadRequestException;
import br.com.omnilink.challange.exception.model.ObjectNotFoundException;
import br.com.omnilink.challange.factory.CostumerFactory;
import br.com.omnilink.challange.model.Costumer;
import br.com.omnilink.challange.repository.costumer.CostumerRepository;
import br.com.omnilink.challange.security.security.UserDetailsLogged;
import br.com.omnilink.challange.service.costumer.CostumerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CostumerServiceImplTest {


    @InjectMocks
    private CostumerServiceImpl costumerService;

    @Mock
    CostumerRepository costumerRepository;

    @Mock
    UserDetailsLogged logged;


    Costumer costumer = null;
    CostumerResponse costumerResponse = null;
    CostumerRequest costumerRequest = null;

    @BeforeEach
    void setUp() {
        costumer = CostumerFactory.getCostumer();
        costumerResponse = CostumerFactory.getResponse();
        costumerRequest = CostumerFactory.getRequest();
    }


    @Test
    void findByIdExisting() {
        when(costumerRepository.findById(1)).thenReturn(Optional.ofNullable(costumer));

        CostumerResponse byId = costumerService.findById(1);

        assertNotNull(byId);

        assertEquals(costumerResponse, byId);
    }


    @Test
    void findByIdNotExisting() {
        when(costumerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> costumerService.findById(1));
    }


    @Test
    void findAllNotEmpty() {
        when(costumerRepository.findAll()).thenReturn(List.of(costumer));

        List<CostumerResponse> all = costumerService.findAll();

        assertEquals(1, all.size());

        assertTrue(all.contains(costumerResponse));
    }


    @Test
    void findAllEmpty() {
        when(costumerRepository.findAll()).thenReturn(List.of());

        List<CostumerResponse> all = costumerService.findAll();

        assertEquals(0, all.size());

        assertFalse(all.contains(costumerResponse));
    }


    @Test
    void save() {
        when(costumerRepository.existsByEmailOrCnpj("exemplo@example.com", "12345678901234")).thenReturn(false);

        assertDoesNotThrow(() -> costumerService.save(costumerRequest));
    }


    @Test
    void dontSave() {
        when(costumerRepository.existsByEmailOrCnpj("exemplo@example.com", "12345678901234")).thenReturn(true);

        assertThrows(BadRequestException.class, () -> costumerService.save(costumerRequest));
    }


    @Test
    void update() {
        when(costumerRepository.findById(1)).thenReturn(Optional.ofNullable(costumer));

        when(costumerRepository.existsByEmailOrCnpjAndId("exemplo@example.com", "12345678901234", 1)).thenReturn(false);

        assertDoesNotThrow(() -> costumerService.update(costumerRequest, 1));

    }

    @Test
    void updateDontExistingCostumer() {
        when(costumerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> costumerService.update(costumerRequest, 1));
    }

    @Test
    void updateExistingEmailOrCnpj() {
        when(costumerRepository.findById(1)).thenReturn(Optional.ofNullable(costumer));

        when(costumerRepository.existsByEmailOrCnpjAndId("exemplo@example.com", "12345678901234", 1)).thenReturn(true);

        assertThrows(BadRequestException.class, () -> costumerService.update(costumerRequest, 1));
    }


    @Test
    void delete() {
        when(costumerRepository.findById(1)).thenReturn(Optional.ofNullable(costumer));

        assertDoesNotThrow(() -> costumerService.delete(1));
    }

    @Test
    void deleteDontExistingCostumer() {
        when(costumerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> costumerService.delete(1));
    }

}