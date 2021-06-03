package com.ssg.homework.t2021hw.store.jpo;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.store.jpo.audit.AuditJpo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Table(name = "PAYMENT")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class PaymentJpo extends AuditJpo {

    @Id
    @Column(name = "PMT_ID")
    @GenericGenerator(name = "PMT_ID", strategy = "com.ssg.homework.t2021hw.store.jpo.CustomIdGenerator")
    @GeneratedValue(generator = "PMT_ID")
    private String pmtId; //결제ID

    @Column(name = "MBR_ID", nullable = false)
    private String mbrId; //회원ID

    @Column(name = "PMT_CODE", nullable = false)
    private String pmtCode; //결제코드

    @Column(name = "BF_PMT_CODE")
    private String bfPmtCode; //이전결제ID

    @Column(name = "PMT_TYPE")
    private String pmtType; //결제타입

    @Column(name = "SUCC_YN")
    private String succYn; //성공여부

    @Column(name = "SUCC_MSG")
    private String succMsg; //성공메세지

    @Column(name = "APRV_TYPE")
    private String aprvType; //승인타입

    @Column(name = "APRV_TIME")
    private Timestamp aprvTime; //승인일시

    @Column(name = "PMT_AMT")
    private long pmtAmt; //결제금액

    public PaymentJpo(PaymentDto paymentDto){
        BeanUtils.copyProperties(paymentDto, this);

    }
    public PaymentDto toDomain() {
        PaymentDto paymentDto = new PaymentDto();
        BeanUtils.copyProperties(this, paymentDto);
        return paymentDto;
    }
}
