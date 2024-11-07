package com.tads.me.util;

import org.apache.commons.lang3.RandomStringUtils;

public class GerarSenhaAleatoria {

    public static String  gerarSenhaAleatoria() {
        return RandomStringUtils.randomNumeric(4);
    }

}
