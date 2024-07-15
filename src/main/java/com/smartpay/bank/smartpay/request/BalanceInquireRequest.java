package com.smartpay.bank.smartpay.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceInquireRequest extends BaseRequest {

    private String channleType;

    @Override
    public String toString() {
        return "BalanceInquireRequest{" +
                "version=" + super.getVersion() +
                ", signType=" + super.getSignType() +
                ", merchantNo='" + super.getMerchantNo() +
                ", date=" + super.getDate() +
                ", channleType=" + channleType +
                ", sign =" + super.getSign() +
                '}';
    }

}
