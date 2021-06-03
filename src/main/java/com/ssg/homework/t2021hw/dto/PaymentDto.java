package com.ssg.homework.t2021hw.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

@ApiModel(description = "PAYMENT ENTITY DTO")
@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {

	@ApiModelProperty(example = "0000000001", notes = "결제ID")
	private String pmtId;

	@ApiModelProperty(example = "0000000345", notes = "회원ID ( MEMBER.MBR_ID 컬럼 참고 )")
	private String mbrId;

	@ApiModelProperty(example = "P0001", notes = "결제코드 ( PAYMENT_MST.PMT_CODE 컬럼 참고 )")
	private String pmtCode;

	@ApiModelProperty(example = "PT02", notes = "결제타입 ( PAYMENT_MST.PMT_TYPE 컬럼 참고 )")
	private String pmtType;

	@ApiModelProperty(example = "Y", notes = "성공여부 ( Y/N ) ")
	private String succYn;

	@ApiModelProperty(example = "처리완료되었습니다", notes = "성공메세지")
	private String succMsg;

	@ApiModelProperty(example = "10", notes = "승인타입 ( 10-승인 / 20-취소 )")
	private String aprvType;

	@ApiModelProperty(example = "1580335864850", notes = "승인일시")
	private Timestamp aprvTime;

	@ApiModelProperty(example = "1574785", notes = "결제금액")
	private Long pmtAmt;


	@ApiModelProperty(example = "0000000001", notes = "이전결제ID")
	private String bfPmtCode;

	private String regpeId; //등록자

	private String modpeId; //수정자

	public PaymentDto(String mbrId, String pmtCode, String pmtType, long pmtAmt, String aprvType){
		this.mbrId = mbrId;
		this.pmtCode = pmtCode;
		this.pmtType = pmtType;
		this.pmtAmt = pmtAmt;
		this.aprvType = aprvType;
	}

	public PaymentDto(String mbrId, String pmtCode, String pmtType, long pmtAmt, String aprvType, String bfPmtId){
		this.mbrId = mbrId;
		this.pmtCode = pmtCode;
		this.pmtType = pmtType;
		this.pmtAmt = pmtAmt;
		this.aprvType = aprvType;
		this.bfPmtCode = bfPmtId;
	}
}
