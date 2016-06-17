package org.givenkind.repository;

import org.givenkind.model.NonProfitCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonprofitCategoryRepository extends JpaRepository<NonProfitCategory, Long>{

	NonProfitCategory findByName(String npCategory);

}
