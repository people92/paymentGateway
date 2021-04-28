package com.ssg.homework.t2021hw.store.jpo;


import com.ssg.homework.t2021hw.dto.PaymentMaster;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT_MST")
@NoArgsConstructor
@Getter
@Setter
@IdClass(PaymentMasterPK.class)
public class PaymentMasterJpo {

    @Id
    @Column(name = "PMT_CODE", nullable = false)
    private String pmtCode; //결제코드

    @Column(name = "PMT_TYPE")
    private String pmtType; //결제타입

    @Id
    @Column(name = "PMT_NAME")
    private String pmtName; //결제코드명

    @Column(name = "PART_CNCL_YN")
    private String partCnclYn; //부분취소가능여부

    public PaymentMasterJpo(PaymentMaster paymentMaster){
        BeanUtils.copyProperties(paymentMaster, this);
    }
    public PaymentMaster toDomain(){
        PaymentMaster paymentMaster = new PaymentMaster();

        BeanUtils.copyProperties(this, paymentMaster);

        return paymentMaster;
    }
}
