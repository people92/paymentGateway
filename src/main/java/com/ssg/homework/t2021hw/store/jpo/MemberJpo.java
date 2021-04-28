package com.ssg.homework.t2021hw.store.jpo;

import com.ssg.homework.t2021hw.dto.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
@Getter
@Setter
public class MemberJpo {

    @Id
    @Column(name = "MBR_ID")
    private String mbrId; //회원ID

    @Column(name = "NAME")
    private String name; //회원명

    public MemberJpo(Member member){
        BeanUtils.copyProperties(member, this);
    }

    public Member toDomain() {
        Member member = new Member();
        BeanUtils.copyProperties(this, member);
        return member;
    }

}
