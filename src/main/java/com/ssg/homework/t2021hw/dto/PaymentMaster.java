package com.ssg.homework.t2021hw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMaster {

    private String pmtCode; //결제코드

    private String pmtType; //결제타입

    private String pmtName; //결제코드명

    private String partCnclYn; //부분취소가능여부

}
