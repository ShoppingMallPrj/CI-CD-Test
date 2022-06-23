package com.example.hello.Dto.In.Item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "상품에 의견 입력 시 사용하는 dto ")
@Data
public class ItemReviewInDto {

    @Schema(description = "유저의 id")
    @NotNull
    private int itemId;

    @Schema(description = "유저의 id")
    @NotNull
    private int userId;

    @Schema(description = "별점", nullable = false)
    @Max(value = 5)
    @Min(value = 0)
    private int reviewStar;

    @Schema(description = "리뷰 내용", nullable = false)
    @Size(max = 100, min = 3, message = "리뷰는 3자 이상 100자 미만으로 입력해주세요!")
    private String reviewContent;

}
