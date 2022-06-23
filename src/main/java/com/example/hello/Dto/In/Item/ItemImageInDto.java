package com.example.hello.Dto.In.Item;

import com.example.hello.Entity.ItemImageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//아이템 이미지 업로드용 dto
@Data
@Schema(description = "아이템 이미지 업로드시 사용하는 dto")
public class ItemImageInDto {

    private int itemImageId;

    //이미지 파일
    @Schema(description = "이미지 파일 MultipartFile 객체", nullable = false)
    @NotNull(message = "이미지 파일이 없습니다.")
    MultipartFile file;
}
