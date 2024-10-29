package com.tads.me.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnviarEmailComSenha {

    @Autowired
    private JavaMailSender mailSender;

    @Transactional
    public void enviarEmailComSenha(String nome, String emailDestino, String senha) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDestino);
            message.setSubject("Sua senha de acesso ao sistema");
            message.setText("Olá " + nome +",\n\nSeu cadastro foi realizado com sucesso! Sua senha de acesso é: " + senha + "\n\nPor favor, mantenha-a em segurança.");
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
