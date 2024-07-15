package com.smartpay.bank.smartpay;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.smartpay.bank.smartpay.configs.SmartPayApiConfiguration;
import com.smartpay.bank.smartpay.request.BalanceInquireRequest;
import com.smartpay.bank.smartpay.response.ApiResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class SmartpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartpayApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


}


//
//@Component
//class Runner implements CommandLineRunner {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
//
//
//	@Override
//	public void run(String... args) throws Exception {
//		callPostToDo();
//
//	}
//
//
//    private void callPostToDo() {
//        RestTemplate restTemplate = new RestTemplate();
//
//
//        // test
//        BalanceInquireRequest balanceInquireRequest = new BalanceInquireRequest();
//        balanceInquireRequest.setVersion("V2");
//        balanceInquireRequest.setSignType("MD5");
//        balanceInquireRequest.setMerchantNo("API2442810283706600");
//        balanceInquireRequest.setDate(new Date());
//        balanceInquireRequest.setChannleType("bo");
//        balanceInquireRequest.setSign("12345");
//
//
//        Map<String, String> params = sortParameters(balanceInquireRequest);
//        String urlString = buildUrlWithParams("https://www.baidu.com", params);
//
//        System.out.println("Printing url string" + urlString);
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<BalanceInquireRequest> entity = new HttpEntity<BalanceInquireRequest>(balanceInquireRequest, headers);
//
//
//
//        try {
//            // Make the POST request and get the response
//            ResponseEntity<ResponseEntity> response =  restTemplate.exchange(urlString, HttpMethod.POST, entity, ResponseEntity.class);
//
//            // Check if the request was successful
//            if (response.getStatusCode() == HttpStatus.OK) {
//                // Process the response body
//                ResponseEntity responseBody = response.getBody();
//                System.out.println("Response: " + responseBody);
//            } else {
//                System.out.println("Request failed with status code: " + response.getStatusCode());
//            }
//        } catch (HttpClientErrorException e) {
//            System.err.println("Request failed with status code: " + e.getStatusCode());
//            System.err.println("Response body: " + e.getResponseBodyAsString());
//            LOGGER.error(e.getMessage());
//        }
//
//        System.out.println("###########################################");
//        System.out.println("Printing POST Response");
//        System.out.println("###########################################");
//
//
//    }
//
//    private Map<String, String> sortParameters(BalanceInquireRequest request) {
//
//        Map<String, String> params = new TreeMap<>();
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//
//        // Format the current date and time
//        String formattedDate = formatter.format(request.getDate());
//        // Add parameters
//        params.put("version", request.getVersion());
//        params.put("signType", request.getSignType());
//        params.put("merchantNo", request.getMerchantNo());
//        params.put("channleType", request.getChannleType());
//        params.put("date", formattedDate);
//        params.put("sign", request.getSign());
//
//        // Sorted by key name in ascending order
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }
//        return params;
//    }
//
//    private String buildUrlWithParams(String baseUrl, Map<String, String> params) {
//        StringBuilder urlBuilder = new StringBuilder(baseUrl);
//
//        if (!params.isEmpty()) {
//            urlBuilder.append("?");
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                urlBuilder.append(entry.getKey())
//                        .append("=")
//                        .append(entry.getValue())
//                        .append("&");
//            }
//            // Remove the last '&'
//            urlBuilder.setLength(urlBuilder.length() - 1);
//        }
//
//        return urlBuilder.toString();
//    }
//}