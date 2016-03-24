package com.jbjohn.spring.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 */
@SuppressWarnings("unused")
public class General {
    private static final Logger LOGGER = LoggerFactory.getLogger(General.class);

    /**
     * MD5 string
     *
     * @param string Sting to be encoded
     * @return string response string
     */
    public static String md5(String string) {
        String md5String = null;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(string));
            md5String = String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Exception encoding string", e);
        }
        return md5String;
    }
}
