package com.smartpay.bank.smartpay.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDTO<T> {

    private ApiResponsCode code;
    private String msg;
    private T detail;

}
