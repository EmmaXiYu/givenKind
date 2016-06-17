package org.givenkind.repository;

import org.givenkind.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

	ItemCategory findByCategoryName(String categoryName);
}
