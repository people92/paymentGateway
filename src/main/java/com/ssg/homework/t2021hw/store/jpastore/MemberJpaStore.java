package com.ssg.homework.t2021hw.store.jpastore;

import com.ssg.homework.t2021hw.store.MemberStore;
import com.ssg.homework.t2021hw.store.jpo.MemberJpo;
import com.ssg.homework.t2021hw.store.repository.MemberJpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class MemberJpaStore implements MemberStore {

    MemberJpaRepository memberJpaRepository;

    public MemberJpaStore(MemberJpaRepository memberJpaRepository){
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public boolean existMemberId(String mbrId) {
        return memberJpaRepository.findByMbrId(mbrId).isPresent();
    }
}
