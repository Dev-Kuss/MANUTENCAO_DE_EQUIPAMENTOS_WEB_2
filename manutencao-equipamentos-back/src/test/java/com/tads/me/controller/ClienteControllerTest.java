package com.tads.me.controller;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.repository.ClienteRepository;
import com.tads.me.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;  // Import this
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private JavaMailSender mailSender;  // Mock the JavaMailSender

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setCpf("12345678900");
        cliente.setEmail("joao.silva@example.com");
        cliente.setTelefone("123456789");
        cliente.setCep("12345-678");
        cliente.setLogradouro("Rua A");
        cliente.setNumero("123");
        cliente.setComplemento("Apto 1");
        cliente.setBairro("Centro");
        cliente.setCidade("Cidade X");
        cliente.setEstado("Estado Y");
    }

    @Test
    void testGetClienteById() throws Exception {
        cliente.setCep("12345-678");
        cliente.setLogradouro("Rua A");
        cliente.setNumero("123");
        cliente.setComplemento("Apto 1");
        cliente.setBairro("Centro");
        cliente.setCidade("Cidade X");
        cliente.setEstado("Estado Y");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/cliente/read/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.email").value("joao.silva@example.com"))
                .andExpect(jsonPath("$.telefone").value("123456789"))
                .andExpect(jsonPath("$.cep").value("12345-678"))
                .andExpect(jsonPath("$.logradouro").value("Rua A"))
                .andExpect(jsonPath("$.numero").value("123"))
                .andExpect(jsonPath("$.complemento").value("Apto 1"))
                .andExpect(jsonPath("$.bairro").value("Centro"))
                .andExpect(jsonPath("$.cidade").value("Cidade X"))
                .andExpect(jsonPath("$.estado").value("Estado Y"));

        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateCliente() throws Exception {
        when(clienteService.createCliente(any(ClienteRequestDTO.class))).thenReturn(cliente);

        String clienteJson = "{ \"nome\": \"João Silva\", \"cpf\": \"12345678900\", \"email\": \"joao.silva@example.com\", \"telefone\": \"123456789\", \"senha\": \"password123\", \"cep\": \"12345-678\", \"rua\": \"Rua A\", \"numero\": \"123\", \"complemento\": \"Apto 1\", \"bairro\": \"Centro\", \"cidade\": \"Cidade X\", \"estado\": \"Estado Y\" }";

        mockMvc.perform(post("/cliente/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.email").value("joao.silva@example.com"))
                .andExpect(jsonPath("$.telefone").value("123456789"))
                .andExpect(jsonPath("$.cep").value("12345-678"))
                .andExpect(jsonPath("$.logradouro").value("Rua A"))
                .andExpect(jsonPath("$.numero").value("123"))
                .andExpect(jsonPath("$.complemento").value("Apto 1"))
                .andExpect(jsonPath("$.bairro").value("Centro"))
                .andExpect(jsonPath("$.cidade").value("Cidade X"))
                .andExpect(jsonPath("$.estado").value("Estado Y"));

        verify(clienteService, times(1)).createCliente(any(ClienteRequestDTO.class));
    }

}
