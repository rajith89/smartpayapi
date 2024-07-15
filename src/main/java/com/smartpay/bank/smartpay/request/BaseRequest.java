package com.smartpay.bank.smartpay.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseRequest {

    private String version = "V2";

    private String signType;

    private String merchantNo;

    private Date date;

    private String sign;

}
