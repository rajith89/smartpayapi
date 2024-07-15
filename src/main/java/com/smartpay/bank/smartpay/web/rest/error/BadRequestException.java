package com.smartpay.bank.smartpay.web.rest.error;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message, Object... objs) {
        super(MessageFormatter.arrayFormat(message, objs).getMessage());
    }

}