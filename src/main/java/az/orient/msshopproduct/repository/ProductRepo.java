package az.orient.msshopproduct.repository;

import az.orient.msshopproduct.entity.CategoryEntity;
import az.orient.msshopproduct.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo  extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByModelId(Long modelId);
    List<ProductEntity> findByModelIdIn(List<Long> modelIds);
    Optional<ProductEntity> findById(long id);
}
