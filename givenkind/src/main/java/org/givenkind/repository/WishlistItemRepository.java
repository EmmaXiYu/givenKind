package org.givenkind.repository;

import java.util.Date;
import java.util.List;

import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.model.UserRole;
import org.givenkind.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long>, JpaSpecificationExecutor<WishlistItem> {

	WishlistItem findById(Long id);

	List<WishlistItem> findByNonProfitUserLogon(NonProfitUserLogon logon);
	
	List<WishlistItem> findAll();
	
	List<WishlistItem> findByDateExpiresGreaterThanEqual(Date date);

	@Query("select wi from WishlistItem wi, NonProfitUserLogon ul " +
           "where wi.dateExpires>=? and wi.nonProfitUserLogon=ul and ul.role=?")
	List<WishlistItem> findByDateExpiresAndUserRole(Date dateExpires, UserRole role);
	
}
