package az.orient.msshopproduct.repository;

import az.orient.msshopproduct.dto.ProductCategoryResponseDto;
import az.orient.msshopproduct.entity.ProductCategoryEntity;
import az.orient.msshopproduct.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepo extends JpaRepository<ProductCategoryEntity, Long> {

    @Query("SELECT new az.orient.msshopproduct.dto.ProductCategoryResponseDto(" +
            "p.productCode, p.price, c.name, parent_c.name) " +
            "FROM ProductCategoryEntity pc " +
            "JOIN pc.product p " +
            "JOIN pc.category c " +
            "LEFT JOIN CategoryEntity parent_c ON c.parentCategory.id = parent_c.id")
    List<ProductCategoryResponseDto> findAllProductCategories();

    @Query("SELECT new az.orient.msshopproduct.dto.ProductCategoryResponseDto(" +
            "p.productCode, p.price, c.name, parent_c.name) " +
            "FROM ProductCategoryEntity pc " +
            "JOIN pc.product p " +
            "JOIN pc.category c " +
            "LEFT JOIN CategoryEntity parent_c ON c.parentCategory.id = parent_c.id " +
            "where pc.id=:productId")
    ProductCategoryResponseDto findProductCategoryByProductId(@Param("productId") Long productId);


    @Query("SELECT new az.orient.msshopproduct.dto.ProductCategoryResponseDto(" +
            "p.productCode, p.price,  c.name, parent_c.name) " +
            "FROM ProductCategoryEntity pc " +
            "JOIN pc.product p " +
            "JOIN pc.category c " +
            "LEFT JOIN CategoryEntity parent_c ON c.parentCategory.id = parent_c.id " +
            "WHERE c.id = :categoryId")
    List<ProductCategoryResponseDto> findByCategory_Id(@Param("categoryId") Long id);

    List<ProductCategoryEntity> findByModelId(Long modelId);

    List<ProductCategoryEntity> findByBrandId(Long brandId);

    List<ProductEntity> findByModelIdIn(List<Long> modelIds);
    ProductCategoryEntity findByProductId(Long productId);


}
