package com.tads.me.controller;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.entity.User;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
        // Simula o retorno de um cliente quando o método getClienteById é chamado
        when(clienteService.getClienteById(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/cliente/read/1")
                        .with(user("admin").password("password").roles("ADMIN"))) // Adiciona autenticação ao teste
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente.getId()))
                .andExpect(jsonPath("$.nome").value(cliente.getNome()))
                .andExpect(jsonPath("$.cpf").value(cliente.getCpf()))
                .andExpect(jsonPath("$.email").value(cliente.getEmail()))
                .andExpect(jsonPath("$.telefone").value(cliente.getTelefone()))
                .andExpect(jsonPath("$.cep").value(cliente.getCep()))
                .andExpect(jsonPath("$.logradouro").value(cliente.getLogradouro()))
                .andExpect(jsonPath("$.numero").value(cliente.getNumero()))
                .andExpect(jsonPath("$.complemento").value(cliente.getComplemento()))
                .andExpect(jsonPath("$.bairro").value(cliente.getBairro()))
                .andExpect(jsonPath("$.cidade").value(cliente.getCidade()))
                .andExpect(jsonPath("$.estado").value(cliente.getEstado()));

        // Verifica se o método getClienteById foi chamado apenas uma vez
        verify(clienteService, times(1)).getClienteById(1L);
    }



    @Test
    void testCreateCliente() throws Exception {
        // Simula o comportamento do serviço para a criação do cliente
        when(clienteService.createCliente(any(ClienteRequestDTO.class), any(User.class))).thenReturn(cliente);

        // Executa o teste com a requisição POST e verifica a resposta
        mockMvc.perform(post("/cliente/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"João Silva\", \"cpf\": \"12345678900\", \"email\": \"joao.silva@example.com\", " +
                                "\"telefone\": \"123456789\", \"senha\": \"password123\", \"cep\": \"12345-678\", " +
                                "\"logradouro\": \"Rua A\", \"numero\": \"123\", \"complemento\": \"Apto 1\", \"bairro\": \"Centro\", " +
                                "\"cidade\": \"Cidade X\", \"estado\": \"Estado Y\" }")
                        .with(csrf()) // Adiciona o token CSRF
                        .with(user("admin").password("password").roles("ADMIN"))) // Adiciona autenticação ao teste
                .andExpect(status().isCreated()) // Espera que a resposta seja 201 CREATED
                .andExpect(jsonPath("$.id").value(cliente.getId()))
                .andExpect(jsonPath("$.nome").value(cliente.getNome()))
                .andExpect(jsonPath("$.cpf").value(cliente.getCpf()))
                .andExpect(jsonPath("$.email").value(cliente.getEmail()))
                .andExpect(jsonPath("$.telefone").value(cliente.getTelefone()))
                .andExpect(jsonPath("$.cep").value(cliente.getCep()))
                .andExpect(jsonPath("$.logradouro").value(cliente.getLogradouro()))
                .andExpect(jsonPath("$.numero").value(cliente.getNumero()))
                .andExpect(jsonPath("$.complemento").value(cliente.getComplemento()))
                .andExpect(jsonPath("$.bairro").value(cliente.getBairro()))
                .andExpect(jsonPath("$.cidade").value(cliente.getCidade()))
                .andExpect(jsonPath("$.estado").value(cliente.getEstado()));

        // Verifica se o método createCliente foi chamado apenas uma vez
        verify(clienteService, times(1)).createCliente(any(ClienteRequestDTO.class), any(User.class));  // Agora passando o mock de User
    }


}

