package com.example.hello.Dto.In.Item;

import com.example.hello.Annotation.Collection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Schema(description = "상품 업로드 시 사용하는 dto")
@Data
@ToString
public class ItemUploadDto {

    @Schema(description = "상품 이름")
    @Size(min = 2, message = "상품 이름은 최소 2자 이상이어야 합니다!")
    @Size(max = 20, message = "상품 이름은 최대 20자 이하여야 합니다!")
    private String itemName;

    @Schema(description = "상품 카테고리")
    @NotBlank
    private String itemCategory;

    @Schema(description = "상품 설명", nullable = false)
    @Size(min = 2, message = "상품 설명은 최소 2자 이상이어야 합니다!")
    @Size(max = 200, message = "상품 설명은 최대 200자 이하여야 합니다!")
    private String itemDescription;

    @Schema(description = "상품 가격", nullable = false)
    @Min(value = 0, message = "상품 가격은 최소 0원 이상이어야 합니다!")
    private int itemPrice;

    //프로필 이미지 파일
    @Schema(description = "프로필 이미지 파일 MultipartFile 객체", nullable = false)
    @NotNull(message = "프로필 이미지 파일이 없습니다.")
    MultipartFile profile;

    //이미지 파일
    @Schema(description = "이미지 파일 MultipartFile 객체", nullable = false)
    //@NotNull(message = "이미지 파일이 없습니다.")
    MultipartFile[] file;

    //이미지 파일
    @Schema(description = "이미지 파일 MultipartFile 객체", nullable = false)
    String[] option;
}
