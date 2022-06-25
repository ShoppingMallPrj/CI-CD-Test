package com.example.hello.Dto.Out.Item;

import com.example.hello.Entity.ItemReviewEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Schema(description = "간략화된 리뷰 데이터")
@Data
public class ItemReviewOutDto {

    @Schema(description = "리뷰 고유 id", nullable = false)
    private int reviewId;

    @Schema(description = "리뷰 작성 시간", nullable = false)
    private LocalDateTime reviewDate;

    @Schema(description = "리뷰 별점 (1~5)", nullable = false)
    private int reviewStar;

    @Schema(description = "리뷰 내용", nullable = false)
    private String reviewContent;

    public static ItemReviewOutDto from(ItemReviewEntity itemReviewEntity, ModelMapper modelMapper) {

        ItemReviewOutDto itemReviewDto = modelMapper.map(itemReviewEntity, ItemReviewOutDto.class);

        return  itemReviewDto;
    }

    public static Set<ItemReviewOutDto> from(Set<ItemReviewEntity> itemReviewEntity, ModelMapper modelMapper) {

        Set<ItemReviewOutDto> itemReviewDtos = new HashSet<>();

        Iterator<ItemReviewEntity> iterator = itemReviewEntity.iterator();

        while (iterator.hasNext()) {
            itemReviewDtos.add(ItemReviewOutDto.from(iterator.next(), modelMapper));
        }
        return  itemReviewDtos;
    }
}
