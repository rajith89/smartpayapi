package com.smartpay.bank.smartpay.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStaemenInquireRequest extends BaseRequest {

    private String channleType;
    private Date beginDate;
    private Date endDate;
    private String orderType;



}
