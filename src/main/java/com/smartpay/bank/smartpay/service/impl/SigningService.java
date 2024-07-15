package com.smartpay.bank.smartpay.service.impl;

import com.smartpay.bank.smartpay.util.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;

@Service
public class SigningService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${smartpay.api.privateKeyStr}")
    private String privateKeyStr;


    public String signString(String input) {

        LOGGER.info("############## private Key Str ####### : " + privateKeyStr);
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            return RSAUtils.sign(input, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to sign the string", e);
        }
    }
}
