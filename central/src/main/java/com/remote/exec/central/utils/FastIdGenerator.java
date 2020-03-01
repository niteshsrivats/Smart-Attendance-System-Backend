package com.remote.exec.central.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class FastIdGenerator {

    public static String generatoFastId(int count, Boolean letters, Boolean numbers) {
        return RandomStringUtils.random(count, letters, numbers);
    }
}
