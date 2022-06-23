package com.example.hello.Entity;

import com.example.hello.Dto.In.Inquiry.InquiryInDto;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

//유저가 올린 문의사항
@Entity
@Table(name = "inquiry")
@Data
public class InquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private int inquiryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    //문의 제목
    @Column(name = "inquiry_title")
    private String inquiryTitle;

    //문의 내용
    @Column(name = "inquiry_content")
    private String inquiryContent;

    //작성시간
    @CreatedDate
    @Column(name = "inquiry_time")
    private LocalDateTime inquiryTime;

    //답변 내용
    @Column(name = "inquiry_answer")
    private String inquiryAnswer;

    //답변되었는지 여부
    private boolean isAnswered;

    public static InquiryEntity from(InquiryInDto inquiryInDto, ModelMapper modelMapper){

        InquiryEntity inquiryEntity = modelMapper.map(inquiryInDto, InquiryEntity.class);

        return inquiryEntity;
    }
}
