package com.tads.me.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;

public class GerarSenhaAleatoria {

    public static String  gerarSenhaAleatoria() {
        return RandomStringUtils.randomNumeric(4);
    }

}
