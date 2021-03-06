package org.givenkind.repository;

import java.util.Date;
import java.util.List;

import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.model.UserRole;
import org.givenkind.model.WishlistItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long>, JpaSpecificationExecutor<WishlistItem> {

	WishlistItem findById(Long id);

	List<WishlistItem> findByNonProfitUserLogon(NonProfitUserLogon logon);
	
	List<WishlistItem> findAll();
	
	@Query("select wi from WishlistItem wi " +
	           "where wi.quantityDesired>'0'")
	List<WishlistItem> findByQuantityDesiredGreaterThan();
		


	List<WishlistItem> findByDateExpiresGreaterThanEqual(Date date);
	
	@Query("select wi from WishlistItem wi, NonProfitUserLogon ul " +
           "where wi.dateExpires>=? and wi.nonProfitUserLogon=ul and ul.role=?")
	List<WishlistItem> findByDateExpiresAndUserRole(Date dateExpires, UserRole role);
	
	@Query("select wi from WishlistItem wi " +
	           "where wi.dateExpires>=? and wi.nonProfitUserLogon=?")
		List<WishlistItem> findByDateExpiresAndNonProfitUserLogon(Date dateExpires, NonProfitUserLogon logon);
	
	@Query("select wi from WishlistItem wi " +
	           "where wi.dateExpires>=? and wi.nonProfitUserLogon=? and wi.quantityDesired>'0'")
		List<WishlistItem> findByDateExpiresAndNonProfitUserLogonAndQuantity(Date dateExpires, NonProfitUserLogon logon);
	
}
