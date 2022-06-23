package com.example.hello.Dto.In.Inquiry;

import com.example.hello.Entity.InquiryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Schema(description = "질문사항 입력 받을 시 Dto")
@Data
public class InquiryInDto {

    @Schema(description = "작성자 고유 id",nullable = false)
    private int userId;

    //문의 제목
    @Schema(description = "문의 제목",nullable = false)
    @Size(min = 5, max = 50, message = "문의사항 제목은 5~50자 제한입니다!")
    private String inquiryTitle;

    //문의 내용
    @Schema(description = "문의 내용",nullable = false)
    @Size(min = 5, max = 100, message = "문의사항은 내용은 5~50자 제한입니다!")
    private String inquiryContent;



}
