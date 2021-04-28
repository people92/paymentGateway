package com.ssg.homework.t2021hw.store.repository;

import com.ssg.homework.t2021hw.store.jpo.MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberJpo, String> {

    Optional<MemberJpo> findByMbrId(String mbrId);

}
