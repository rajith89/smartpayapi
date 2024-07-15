package com.smartpay.bank.smartpay.web.rest;


import com.smartpay.bank.smartpay.request.BalanceInquireRequest;
import com.smartpay.bank.smartpay.response.ApiException;
import com.smartpay.bank.smartpay.service.impl.SmartPayService;
import com.smartpay.bank.smartpay.web.rest.error.BadRequestException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Partner Screening Rest Resource
 *
 * @author <a href="mailto:pemabandu@gmail.com">Rajith Pemabandu</a>
 */
@RestController
@Tag(name = "Smart Pay ", description = "Smart Pay Banking APIs")
@RequestMapping(SmartPayApiResource.BANK_API)
@AllArgsConstructor
public class SmartPayApiResource {

    public static final String BANK_API = "/api/smartpay";

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SmartPayService smartPayService;


    @PostMapping(value = "/balanceInquiry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBalance(@RequestBody BalanceInquireRequest request) throws ApiException {
        if (Objects.isNull(request)) {
            throw new BadRequestException("BalanceInquireRequest parameters not completed.");
        }
        LOGGER.info("SmartPayApiResource > Invoked Endpoint - balanceInquiry with request - {}");
        return (smartPayService.balanceInquire(request));
    }



}
