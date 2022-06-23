package com.example.hello.Repository;

import com.example.hello.Entity.InquiryEntity;
import com.example.hello.Entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Integer>{

    //전부다 읽어온다.
    Page<InquiryEntity> findAll(Pageable pageable);

    //유저 아이디 기준으로 읽어온다.
    @Query(value = "select i from InquiryEntity i where i.userEntity.userId = :userId ")
    Set<InquiryEntity> readAllByUserId(int userId);

    //질의응답 고유 id 기준으로 읽어온다.
    InquiryEntity readByInquiryId(int inquiryId);



}
