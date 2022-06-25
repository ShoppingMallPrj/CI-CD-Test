package com.example.hello.Service;

import com.example.hello.Dto.In.Inquiry.InquiryAnswerInDto;
import com.example.hello.Dto.In.Inquiry.InquiryInDto;
import com.example.hello.Dto.Out.Inquiry.InquiryDetailOutDto;
import com.example.hello.Dto.Out.Inquiry.InquiryOutDto;
import com.example.hello.Entity.InquiryEntity;
import com.example.hello.Repository.InquiryRepository;
import com.example.hello.Repository.UserRepository;
import com.example.hello.Util.ModelMapperBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class InquiryService {

    @Autowired
    InquiryRepository inquiryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapperBean modelMapperBean;

    public Page<InquiryOutDto> readAll(Pageable pageable){

        Page<InquiryEntity> inquiryEntityPage = inquiryRepository.findAll(pageable);
        Page<InquiryOutDto> inquiryDtos = InquiryOutDto.from(inquiryEntityPage, modelMapperBean.modelMapper());
        return inquiryDtos;
    }

    //특정 유저가 가진 문의사항을 전부 가져온다.
    public Set<InquiryOutDto> readUserInquiry(int userId){

        Set<InquiryEntity> inquiryEntitySet  = inquiryRepository.readAllByUserId(userId);
        Set<InquiryOutDto> inquiryDtos = InquiryOutDto.from(inquiryEntitySet, modelMapperBean.modelMapper());

        return inquiryDtos;
    }

    //문의사항을 하나 가져온다.
    public InquiryDetailOutDto read(int inquiryId){

        InquiryEntity inquiryEntity =  inquiryRepository.readByInquiryId(inquiryId);
        return InquiryDetailOutDto.from(inquiryEntity, modelMapperBean.modelMapper());
    }

    @Transactional
    public InquiryEntity create(int userId, InquiryInDto inquiryInDto){

        InquiryEntity inquiryEntity = new InquiryEntity();

        inquiryEntity.setUserEntity(userRepository.findByUserId(userId));
        inquiryEntity.setInquiryTitle(inquiryInDto.getInquiryTitle());
        inquiryEntity.setInquiryContent(inquiryInDto.getInquiryContent());
        inquiryEntity.setSecret(inquiryEntity.isSecret());
        inquiryEntity.setInquiryPw(inquiryInDto.getInquiryPw());
        inquiryEntity.setAnswered(false);

        return inquiryRepository.save(inquiryEntity);
    }

    //문의사항에 답변을 단다.
    @Transactional
    public void answerInquiry(InquiryAnswerInDto inquiryAnswerInDto, int inquiryId){

        InquiryEntity inquiryEntity = inquiryRepository.readByInquiryId(inquiryId);
        inquiryEntity.setAnswered(true);
        inquiryEntity.setInquiryAnswer(inquiryAnswerInDto.getAnswer());
    }
}
