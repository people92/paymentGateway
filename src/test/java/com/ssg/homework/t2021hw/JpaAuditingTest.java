package com.ssg.homework.t2021hw;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.store.jpo.PaymentJpo;
import com.ssg.homework.t2021hw.store.repository.PaymentJpaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaAuditingTest {

    @Autowired
    PaymentJpaRepository paymentJpaRepository;

    @Test
    @DisplayName("audit 생성자/수정자 ID 테스트")
    public void auditingSaveTest() {
        PaymentDto paymentDto = new PaymentDto("0000000345", "P0002", null, 157400, "10");

        PaymentJpo result = paymentJpaRepository.save(new PaymentJpo(paymentDto));

        Assert.assertEquals(result.getModpeId(),"payment-gateway");
        Assert.assertEquals(result.getRegpeId(),"payment-gateway");

    }
}
