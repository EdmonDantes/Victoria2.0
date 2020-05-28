package ru.liveproduction.victoria.core.entity.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liveproduction.victoria.core.entity.category.impl.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
