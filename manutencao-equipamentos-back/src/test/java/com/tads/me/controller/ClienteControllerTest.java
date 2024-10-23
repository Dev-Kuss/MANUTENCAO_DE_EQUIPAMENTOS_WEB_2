package com.tads.me.controller;

import com.tads.me.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.entity.Cliente;
import com.tads.me.service.ClienteService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private JavaMailSender mailSender;

    private Cliente cliente;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        cliente = new Cliente();
        cliente.setId(UUID.fromString("41d6c22f-3682-49ee-8d88-c8505f5f3806")); // Ensure this UUID matches test UUID
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

        // Mock the clienteService behavior
        Mockito.when(clienteService.getClienteById(cliente.getId())).thenReturn(Optional.of(cliente));
        Mockito.when(clienteService.createCliente(Mockito.any(ClienteRequestDTO.class))).thenReturn(cliente);
    }

    @Test
    @WithMockUser // Use this annotation to mock an authenticated user
    void testGetClienteById() throws Exception {
        mockMvc.perform(get("/cliente/read/{id}", "41d6c22f-3682-49ee-8d88-c8505f5f3806"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser // Use this annotation to mock an authenticated user
    void testCreateCliente() throws Exception {
        mockMvc.perform(post("/cliente/create")
                        .contentType("application/json")
                        .content("{\"nome\": \"João Silva\", \"cpf\": \"12345678900\", \"email\": \"joao.silva@example.com\", \"telefone\": \"123456789\", \"senha\": \"senha123\", \"cep\": \"12345-678\", \"logradouro\": \"Rua A\", \"numero\": \"123\", \"complemento\": \"Apto 1\", \"bairro\": \"Centro\", \"cidade\": \"Cidade X\", \"estado\": \"Estado Y\"}")
                        .with(csrf())) // Include CSRF token
                .andExpect(status().isCreated());
    }
}