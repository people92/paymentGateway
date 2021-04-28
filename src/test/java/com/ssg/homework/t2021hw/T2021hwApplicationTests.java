package com.ssg.homework.t2021hw;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.service.PaymentGatewayService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class T2021hwApplicationTests {

	@Autowired
	private PaymentGatewayService paymentGatewayService;



	int successCnt = 0;
	int failCnt = 0;

	@Test
	void validationTest() {
		String[] memberId = new String[]{"0000000299","0000000352","0000000562","0000000602","0000000780"};

		for(int i = 0 ; i < memberId.length; i++) {
			//승인결재 9개
			PaymentDto approvePaymentTest1 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);
			PaymentDto approvePaymentTest2 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);
			PaymentDto approvePaymentTest3 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);
			PaymentDto approvePaymentTest4 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);
			PaymentDto approvePaymentTest5 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);
			PaymentDto approvePaymentTest6 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);
			PaymentDto approvePaymentTest7 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);
			PaymentDto approvePaymentTest8 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);
			PaymentDto approvePaymentTest9 = paymentGatewayService.approve(memberId[i], "P0001", "PT01", 157400);

			//전체취소 7개
			PaymentDto cancelPaymentTest1 = paymentGatewayService.cancel(memberId[i],approvePaymentTest1.getPmtId(), 157400);
			PaymentDto cancelPaymentTest2 = paymentGatewayService.cancel(memberId[i],approvePaymentTest2.getPmtId(), 157400);
			PaymentDto cancelPaymentTest3 = paymentGatewayService.cancel(memberId[i],approvePaymentTest3.getPmtId(), 157400);
			PaymentDto cancelPaymentTest4 = paymentGatewayService.cancel(memberId[i],approvePaymentTest4.getPmtId(), 157400);
			PaymentDto cancelPaymentTest5 = paymentGatewayService.cancel(memberId[i],approvePaymentTest5.getPmtId(), 157400);
			PaymentDto cancelPaymentTest6 = paymentGatewayService.cancel(memberId[i],approvePaymentTest6.getPmtId(), 157400);
			PaymentDto cancelPaymentTest7 = paymentGatewayService.cancel(memberId[i],approvePaymentTest7.getPmtId(), 157400);

			//부분취소 3개
			PaymentDto partialCancelPaymentTest1 = paymentGatewayService.partialCancel(memberId[i], approvePaymentTest8.getPmtId(), 5000);
			PaymentDto partialCancelPaymentTest2 = paymentGatewayService.partialCancel(memberId[i], approvePaymentTest8.getPmtId(), 5000);
			PaymentDto partialCancelPaymentTest3 = paymentGatewayService.partialCancel(memberId[i], approvePaymentTest9.getPmtId(), 5000);

			plusSuccesOrFail(approvePaymentTest1);
			plusSuccesOrFail(approvePaymentTest2);
			plusSuccesOrFail(approvePaymentTest3);
			plusSuccesOrFail(approvePaymentTest4);
			plusSuccesOrFail(approvePaymentTest5);
			plusSuccesOrFail(approvePaymentTest6);
			plusSuccesOrFail(approvePaymentTest7);
			plusSuccesOrFail(approvePaymentTest8);
			plusSuccesOrFail(approvePaymentTest9);

			plusSuccesOrFail(cancelPaymentTest1);
			plusSuccesOrFail(cancelPaymentTest2);
			plusSuccesOrFail(cancelPaymentTest3);
			plusSuccesOrFail(cancelPaymentTest4);
			plusSuccesOrFail(cancelPaymentTest5);
			plusSuccesOrFail(cancelPaymentTest6);
			plusSuccesOrFail(cancelPaymentTest7);


			plusSuccesOrFail(partialCancelPaymentTest1);
			plusSuccesOrFail(partialCancelPaymentTest2);
			plusSuccesOrFail(partialCancelPaymentTest3);


		}

		int successResult = paymentGatewayService.countPaymentBySuccYn("Y");
		int failResult = paymentGatewayService.countPaymentBySuccYn("N");
        System.out.println("successCnt : " +successCnt);
        System.out.println("successResult : " + successResult);
        System.out.println("failCnt : " + failCnt);
        System.out.println("failResult : " + failResult);

		Assert.assertEquals(successCnt, successResult);
		Assert.assertEquals(failCnt, failResult);


	}

	void plusSuccesOrFail(PaymentDto paymentDto){
		if("Y".equals(paymentDto.getSuccYn())){
			successCnt++;
		}else {
			failCnt++;
		}
	}

}
