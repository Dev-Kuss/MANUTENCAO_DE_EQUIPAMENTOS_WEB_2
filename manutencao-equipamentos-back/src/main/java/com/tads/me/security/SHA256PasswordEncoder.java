package com.tads.me.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SHA256PasswordEncoder implements PasswordEncoder {

    // Método encode agora usa SaltUtil para gerar um salt dinâmico
    @Override
    public String encode(CharSequence rawPassword) {
        String salt = SaltUtil.generateSalt(); // Gera salt dinâmico
        String hashedPassword = encodeWithSalt(rawPassword, salt);
        // Concatena o salt com a senha codificada para armazenamento
        return salt + ":" + hashedPassword;
    }

    public boolean matchesWithSalt(CharSequence rawPassword, String salt, String encodedPassword) {
        String hashedPassword = encodeWithSalt(rawPassword, salt);
        return hashedPassword.equals(encodedPassword);
    }
    // Método matches que separa o salt armazenado e verifica a senha
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Divide salt e senha codificada
        String[] parts = encodedPassword.split(":");
        if (parts.length != 2) {
            return false; // Formato inválido
        }
        String salt = parts[0];
        String hash = parts[1];
        // Codifica novamente com o salt extraído e compara
        return encodeWithSalt(rawPassword, salt).equals(hash);
    }

    // Método encodeWithSalt existente
    public String encodeWithSalt(CharSequence rawPassword, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String saltedPassword = rawPassword + salt;
            byte[] hash = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao codificar senha: algoritmo SHA-256 não encontrado.", e);
        }
    }

    // Classe utilitária para geração de salt
    public static class SaltUtil {
        private static final int SALT_LENGTH = 16;

        public static String generateSalt() {
            byte[] salt = new byte[SALT_LENGTH];
            new SecureRandom().nextBytes(salt);
            return Base64.getEncoder().encodeToString(salt);
        }
    }
}
