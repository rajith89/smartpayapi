package com.smartpay.bank.smartpay.service.impl;

import com.smartpay.bank.smartpay.request.BalanceInquireRequest;
import com.smartpay.bank.smartpay.response.ApiResponsCode;
import com.smartpay.bank.smartpay.response.ApiResponseDTO;
import com.smartpay.bank.smartpay.response.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

@Service
public class SmartPayService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${smartpay.api.baseUrl}")
    private String baseurl;

    @Value("${smartpay.api.port}")
    private String port;

    @Value("${smartpay.api.hashKey}")
    private String hashKey;


    private final RestTemplate restTemplate;

    @Autowired
    private SigningService signingService;

    public SmartPayService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<String> balanceInquire(BalanceInquireRequest balanceInquireRequest) throws ApiException {

        LOGGER.info("############## BalanceInquireRequest : ##############  : " + balanceInquireRequest.toString());
        Map<String, String> params = sortParameters(balanceInquireRequest);
        String urlString = buildUrlWithParams(baseurl, params);
        String tobeSigned = urlString.concat(hashKey);
        LOGGER.info("############## String tobeSigned: ##############  : " + tobeSigned);
        String signedString = signingService.signString(tobeSigned);
        LOGGER.info("############## Signed String : ##############  : " + signedString);
        String balanceAPIurl = baseurl + ":" + port + "/api/balance/V2";
        /*
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BalanceInquireRequest> balanceInquireentity = new HttpEntity<BalanceInquireRequest>(balanceInquireRequest, headers);
        */
        try {
            //  ResponseEntity<ApiResponseDTO<?>> response =  restTemplate.exchange(urlString, HttpMethod.POST, balanceInquireentity,  new ParameterizedTypeReference<ApiResponseDTO<?>>());
            ApiResponseDTO<String> response = getApiResponse(balanceAPIurl, String.class, signedString);

            if (response.getCode() == ApiResponsCode.SUCCESS) {
                // Process the response body
                LOGGER.info("Message: " + response.getMsg());
                LOGGER.info("Detail: " + response.getDetail());

            } else {
                LOGGER.error("Request failed with status code: " + response.getCode());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response.getDetail());


        } catch (Exception e) {
            LOGGER.error("Failed to get Balance Inquiry: " + e.getMessage());
            throw new ApiException(e.getMessage());
        }
    }


    private <T> ApiResponseDTO<T> getApiResponse(String url, Class<T> responseType, String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

        ResponseEntity<ApiResponseDTO<T>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ApiResponseDTO<T>>() {
                }
        );
        return responseEntity.getBody();
    }


    private Map<String, String> sortParameters(BalanceInquireRequest request) {

        Map<String, String> params = new TreeMap<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        // Format the current date and time
        String formattedDate = formatter.format(request.getDate());
        // Add parameters
        params.put("version", request.getVersion());
        params.put("signType", request.getSignType());
        params.put("merchantNo", request.getMerchantNo());
        params.put("channleType", request.getChannleType());
        params.put("date", formattedDate);
        params.put("sign", request.getSign());

        // Sorted by key name in ascending order
        for (Map.Entry<String, String> entry : params.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        return params;
    }

    private String buildUrlWithParams(String baseUrl, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder();

        if (!params.isEmpty()) {
            // urlBuilder.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            // Remove the last '&'
            urlBuilder.setLength(urlBuilder.length() - 1);
        }

        return urlBuilder.toString();
    }
}
