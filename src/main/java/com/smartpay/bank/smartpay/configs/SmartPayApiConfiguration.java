package com.smartpay.bank.smartpay.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartPayApiConfiguration {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${smartpay.api.merchantNo}")
    private String merchantNo;

    @Value("${smartpay.api.hashKey}")
    private String hashKey;

    @Value("${smartpay.api.baseUrl}")
    private String baseUrl;

    @Value("${smartpay.api.Key}")
    private String Key;

    public String getMerchantNo() {
        return merchantNo;
    }

    public String getHashKey() {
        return hashKey;
    }

    public String getKey() {
        return Key;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
