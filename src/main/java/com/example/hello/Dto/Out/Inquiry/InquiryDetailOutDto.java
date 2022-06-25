package com.example.hello.Dto.Out.Inquiry;

import com.example.hello.Entity.InquiryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Schema(description = "문의사항 클라이언트 전달 시 사용하는 dto")
@Data
@ToString
public class InquiryDetailOutDto {

    @Schema(description = "문의 사항 고유 id",nullable = false)
    private int inquiryId;

    @Schema(description = "작성자 고유 id",nullable = false)
    private int userId;

    @Schema(description = "작성자 고유 이름",nullable = false)
    private String userEmail;

    //문의 제목
    @Schema(description = "문의 제목",nullable = false)
    @Size(min = 5, max = 50, message = "문의사항 제목은 5~50자 제한입니다!")
    private String inquiryTitle;

    //문의 내용
    @Schema(description = "문의 내용",nullable = false)
    @Size(min = 5, max = 100, message = "문의사항은 내용은 5~50자 제한입니다!")
    private String inquiryContent;

    @Schema(description = "답변됨 여부",nullable = false)
    @NotNull
    private boolean isAnswered;

    //작성시간
    @Schema(description = "작성 시간",nullable = false)
    private LocalDateTime inquiryTime;

    //답변 내용
    @Schema(description = "답변 내용",nullable = false)
    private String inquiryAnswer;

    //답변 내용
    @Schema(description = "비밀글 여부",nullable = false)
    private boolean isSecret;

    //문의 비밀번호
    @Schema(description = "답변 비밀번호",nullable = false)
    private String inquiryPw;

    //Entity에서 dto 변환
    public static InquiryDetailOutDto from(InquiryEntity inquiryEntity, ModelMapper modelMapper){

        InquiryDetailOutDto inquiryDetailOutDto = modelMapper.map(inquiryEntity, InquiryDetailOutDto.class);
        inquiryDetailOutDto.setUserId(inquiryEntity.getUserEntity().getUserId());
        inquiryDetailOutDto.setUserEmail(inquiryEntity.getUserEntity().getUserEmail());
        return inquiryDetailOutDto;
    }

}
