package com.ssg.homework.t2021hw.store.jpo.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class AuditJpo {

    @CreatedBy
    @Column(name = "REGPE_ID")
    private String regpeId; //등록자

    @CreatedDate
    @Column(name = "REG_DTS")
    private Date regDts;    //등록일시

    @LastModifiedBy
    @Column(name = "MODPE_ID")
    private String modpeId; //수정자

    @LastModifiedDate
    @Column(name = "MOD_DTS")
    private Date modDts;  //수정일시

    public void setModpeId(String modpeId) {
        this.modpeId = modpeId;
    }

    public void setRegpeId(String regpeId) {
        this.regpeId = regpeId;
    }
}
