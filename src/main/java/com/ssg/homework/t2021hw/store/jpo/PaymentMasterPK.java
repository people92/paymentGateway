package com.ssg.homework.t2021hw.store.jpo;

import lombok.Data;
import java.io.Serializable;

@Data
public class PaymentMasterPK implements Serializable {

    private String pmtCode; //결제코드

    private String pmtName; //결제코드명
}
