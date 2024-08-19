package az.orient.msshopproduct.repository;

import az.orient.msshopproduct.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findById(long id);




}
