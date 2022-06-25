package com.example.hello.Repository;

import com.example.hello.Entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    @Query(value = "select i from ItemEntity i left join fetch i.option o where o.isDeleted = false and i.isDeleted = false")
    Set<ItemEntity> findAllByCondition();

    //모든 상품을 읽어온다. 삭제되지 않은 아이템과 옵션만 검색해서 가져온다.
    @Query(value = "select i from ItemEntity i left join fetch i.option o where o.isDeleted = false and i.isDeleted = false",
            countQuery = "select count(i) from ItemEntity i where i.isDeleted = false")
    Page<ItemEntity> findAllByPage(Pageable pageable);

    // 각 성별의 모든 상품을 읽어온다. 삭제되지 않은 아이템과 옵션만 검색해서 가져온다.
    @Query(value = "select i from ItemEntity i left join fetch i.option o where o.isDeleted = false and i.isDeleted = false",
            countQuery = "select count(i) from ItemEntity i where i.isDeleted = false")
    Page<ItemEntity> findAllByGender(String gender, Pageable pageable);

    //상품명 키워드로 상품 검색
    @Query(value = "select i from ItemEntity i left join fetch i.option o " +
            "where o.isDeleted = false and i.isDeleted = false and i.itemName like %:keyword%",
            countQuery = "select count(i) from ItemEntity i where i.isDeleted = false and i.itemName like %:keyword%")
    Page<ItemEntity> findItemWithSearch(String keyword, Pageable pageable);

    //연관된 상품
    Set<ItemEntity> findByItemCategoryAndGender(String category, String gender);
}
