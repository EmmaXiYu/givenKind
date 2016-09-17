package org.givenkind.repository;

import java.util.Date;
import java.util.List;

import org.givenkind.model.DonorlistItem;
import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.model.UserLogon;
import org.givenkind.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DonorlistItemRepository extends JpaRepository<DonorlistItem, Long>, JpaSpecificationExecutor<DonorlistItem> {
	
	DonorlistItem findById(Long id);
	
	List<DonorlistItem> findByUser(UserLogon user);
	
	List<DonorlistItem> findAll();
	
	@Query("select di from DonorlistItem di " +
	           "where di.dateExpires>=? and di.user=?")
		List<DonorlistItem> findByDateExpiresAndUser(Date dateExpires, UserLogon user);
}
