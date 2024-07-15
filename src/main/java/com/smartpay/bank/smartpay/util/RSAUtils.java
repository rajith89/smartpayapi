package com.smartpay.bank.smartpay.util;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class RSAUtils {

    private static final String SIGNING_ALGORITHM = "SHA256withRSA";

    public static String sign(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signedData = signature.sign();
        return Base64.getEncoder().encodeToString(signedData);
    }
}
