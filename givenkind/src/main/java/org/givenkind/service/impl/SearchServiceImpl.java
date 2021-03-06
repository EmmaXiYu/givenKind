package org.givenkind.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.givenkind.dto.SearchCriteriaDTO;
import org.givenkind.model.DonorUserLogon;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.ItemCategory;
import org.givenkind.model.NonProfitCategory;
import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.model.Profile;
import org.givenkind.model.UserLogon;
import org.givenkind.model.WishlistItem;
import org.givenkind.repository.DonorlistItemRepository;
import org.givenkind.repository.ItemCategoryRepository;
import org.givenkind.repository.NonprofitCategoryRepository;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.repository.WishlistItemRepository;
import org.givenkind.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

	private static final int NUMBER_OF_SEARCH_RESULTS_PER_PAGE = 10;

	@Autowired
	UserLogonRepository userRepository;

	@Autowired
	WishlistItemRepository wishlistItemRepository;

	@Override
	public SearchCriteriaDTO prepareSearchPage(String userName) {
		SearchCriteriaDTO searchDTO = new SearchCriteriaDTO();

		searchDTO.setDistance(0);
		searchDTO.setItemCategories(Collections.<String> emptyList());
		searchDTO.setKeyword("");
		searchDTO.setNonprofitCategories(Collections.<String> emptyList());
		searchDTO.setPickUpService(false);
		searchDTO.setZipCode("");

		return searchDTO;
	}

	@Autowired
	private DonorlistItemRepository donorItemRepository;

	// TODO: how do we account for distance???
	private static Specification<DonorlistItem> createDonorlistItemDistanceSpecification(final int distance) {
		return new Specification<DonorlistItem>() {
			@Override
			public Predicate toPredicate(Root<DonorlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(cb.literal(1), cb.literal(1));
			}
		};
	}

	private static Specification<WishlistItem> createWishlistItemDistanceSpecification(final int distance) {
		return new Specification<WishlistItem>() {
			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(cb.literal(1), cb.literal(1));
			}
		};
	}

	private <T> Specifications<T> joinSpecifications(List<Specification<T>> lst) {
		if (lst == null || lst.isEmpty()) {
			return Specifications.where(new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(cb.literal(1), cb.literal(1));
				}
			});
		}
		
		
		Specifications<T> spec = Specifications.where(lst.get(0));
		//For Donor Search
		// (Keyword spec or item spec or NP name Spec)and zipcode, item category, NP category,distance,pickupservice, date)
		if(lst.size()== 10){
		for (int i = 1; i <=2; i++) {
			spec = spec.or(lst.get(i));
		}
		
		for (int i = 3; i < lst.size(); i++) {
			spec = spec.and(lst.get(i));
		}
		}//For NP Search
		// (Keyword spec or item spec)and zipcode, item category,distance, date)
		else{
			spec = spec.or(lst.get(1));
			for (int i = 2; i < lst.size(); i++) {
				spec = spec.and(lst.get(i));
			}
			
		}
		
		return spec;
	}

	
	public Page<DonorlistItem> processSearchForNonProfit(SearchCriteriaDTO searchDTO) {

		log.info("processing search for non-profit");
		Pageable p = new PageRequest(searchDTO.getPageNumber(), NUMBER_OF_SEARCH_RESULTS_PER_PAGE, Sort.Direction.DESC,
				"id");

		Specification<DonorlistItem> distanceSpec, itemCategoriesSpec, keywordSpec,
				 zipCodeSpec,dateExpiresSpec, itemDespSpec,quantitySpec;

		distanceSpec = (createDonorlistItemDistanceSpecification(searchDTO.getDistance()));
		itemCategoriesSpec = (withDonorItemCategories(searchDTO.getItemCategories()));		
		keywordSpec = (createDonorlistItemKeywordSpecification(searchDTO.getKeyword()));
		itemDespSpec = (createDonorlistItemDescriptionSpecification(searchDTO.getKeyword()));		
		zipCodeSpec = (createDonorlistItemZipcodeSpecification(searchDTO.getZipCode()));
		dateExpiresSpec = (createDonorlistItemDateExpiresSpecification(new Date()));
		quantitySpec = (createDonorlistItemQuantitySpecification(0));

		List<Specification<DonorlistItem>> lst = Arrays.asList(itemDespSpec,keywordSpec,distanceSpec, itemCategoriesSpec,
				 zipCodeSpec,dateExpiresSpec,quantitySpec );

		return donorItemRepository.findAll(this.joinSpecifications(lst), p);
	}

	private Specification<DonorlistItem> createDonorlistItemZipcodeSpecification(final String zipCode) {
		return new Specification<DonorlistItem>() {

			@Override
			public Predicate toPredicate(Root<DonorlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(zipCode == null || zipCode.trim().isEmpty()) {
					return cb.equal(cb.literal(1), cb.literal(1));
				} else {
					
					return cb.equal(root.get("user").get("profile").get("zipCode").as(String.class), cb.literal(zipCode));

				}
			}
		};
	}
	private Specification<DonorlistItem> createDonorlistItemDateExpiresSpecification(final Date date) {
		return new Specification<DonorlistItem>() {

			@Override
			public Predicate toPredicate(Root<DonorlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(date == null) {
					return cb.equal(cb.literal(1), cb.literal(1));
				} else {
					
					return cb.greaterThanOrEqualTo(root.get("dateExpires").as(Date.class), cb.literal(date));

				}
			}
		};
	}
	private Specification<DonorlistItem> createDonorlistItemQuantitySpecification(final Integer qty) {
		return new Specification<DonorlistItem>() {

			@Override
			public Predicate toPredicate(Root<DonorlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					
					return cb.greaterThan(root.get("quantity").as(Integer.class),qty);
				}
			
		};
	}


	private Specification<DonorlistItem> createDonorlistItemDescriptionSpecification(final String keyword) {
		return new Specification<DonorlistItem>() {

			@Override
			public Predicate toPredicate(Root<DonorlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(keyword == null || keyword.isEmpty()) {
					return cb.equal(cb.literal(1), cb.literal(1));
				}
				
				return cb.like(cb.upper(root.get("description").as(String.class)), cb.upper(cb.literal("%"+keyword+"%")));
				
				
			}
		};
	}
	private Specification<DonorlistItem> createDonorlistItemKeywordSpecification(final String keyword) {
		return new Specification<DonorlistItem>() {

			@Override
			public Predicate toPredicate(Root<DonorlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(keyword == null || keyword.isEmpty()) {
					return cb.equal(cb.literal(1), cb.literal(1));
				}
				
				return cb.like(cb.upper(root.get("itemName").as(String.class)), cb.upper(cb.literal("%"+keyword+"%")));
				
				
			}
		};
	}
	
	@Autowired
	NonprofitCategoryRepository nonProfitCategoryRepo;
	
	@Autowired
	ItemCategoryRepository itemCategoryRepo;
	
	@Transactional
	private List<ItemCategory> fetchItemCategories(List<String> lst) {
		List<ItemCategory> categories = new ArrayList<ItemCategory>();
		for (String n : lst) {
			ItemCategory c = itemCategoryRepo.findByCategoryName(n);
			if (c != null) {
				categories.add(c);
			}
		}
		return categories;
	}
	
	@Transactional
	private List<NonProfitCategory> fetchNonProfitCategories(List<String> lst) {
		List<NonProfitCategory> categories = new ArrayList<NonProfitCategory>();
		for (String n : lst) {
			NonProfitCategory c = nonProfitCategoryRepo.findByName(n);
			if (c != null) {
				categories.add(c);
			}
		}
		return categories;
	}

	/*private Specification<DonorlistItem> withDonorNonProfitCategories(
			final List<String> nonprofitCategories) {
		
		return new Specification<DonorlistItem>() {

			@Override
			public Predicate toPredicate(Root<DonorlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				
				
				if(nonprofitCategories == null || nonprofitCategories.isEmpty())
					return cb.equal(cb.literal(1), cb.literal(1));
				
				Join<DonorlistItem, ItemCategory> join = root.join("itemCategories");
				return join.get("categoryName").as(String.class).in(nonprofitCategories);
			}
		};
	}*/

	
	private Specification<DonorlistItem> withDonorItemCategories(
			final List<String> itemCategories) {
		
		return new Specification<DonorlistItem>() {

			@Override
			public Predicate toPredicate(Root<DonorlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(itemCategories == null || itemCategories.isEmpty()) {
					return cb.equal(cb.literal(1), cb.literal(1));
				}
				
				query.distinct(true);
				
				Join<DonorlistItem, ItemCategory> j1 = root.join("itemCategories");
				
				return j1.get("categoryName").as(String.class).in(itemCategories);
				
//				Subquery<DonorlistItem> icq = query.subquery(DonorlistItem.class);
//				Root<ItemCategory> icr = icq.from(ItemCategory.class);
//				
//				Join<ItemCategory, DonorlistItem> joined = icr.join("donorlistItems");
//				
//				ItemCategory.
//				icq.select(joined).where(icr.get("categoryName").in(itemCategories));
//				
//				return cne.in(icq);
				
				/*Subquery<ItemCategory> sq = query.subquery(ItemCategory.class);
				Root<ItemCategory> icr = sq.from(ItemCategory.class);
				sq = sq.select(icr).where(icr.get("categoryName").in(itemCategories));
				
				
				
				Path<List<ItemCategory>> categories = root.get("itemCategories");
				
				
				//return cb.isNotEmpty(cb.diff(categories, sq));
				//query.where(cb.in(categories).value(value))
				
				return null;*/
			}
		};
	}

	
	public Page<WishlistItem> processSearchForDonor(SearchCriteriaDTO searchDTO) {
		
		log.info("processing search for donor");
		
		Pageable p = new PageRequest(searchDTO.getPageNumber(), NUMBER_OF_SEARCH_RESULTS_PER_PAGE, Sort.Direction.DESC,
				"id");

		Specification<WishlistItem> distanceSpec, itemCategoriesSpec, nonProfitCategoriesSpec, keywordSpec,
				pickUpServiceSpec, zipCodeSpec,dateExpiresSpec,itemDespSpec,nonProfitNameSpec,quantitySpec;

		distanceSpec = (createWishlistItemDistanceSpecification(searchDTO.getDistance()));
		itemCategoriesSpec = (createWishlistItemItemCategoriesSpecification(searchDTO.getItemCategories()));
		nonProfitCategoriesSpec = (createWishlistItemNonProfitCategoriesSpecification(
				searchDTO.getNonprofitCategories()));
		keywordSpec = (createWishlistItemKeywordSpecification(searchDTO.getKeyword()));
		itemDespSpec = (createWishlistItemItemDespSpecification(searchDTO.getKeyword()));
		nonProfitNameSpec = (createWishlistNonProfitNameSpecification(searchDTO.getKeyword()));
		pickUpServiceSpec = (createWishlistItemPickupServiceSpecification(searchDTO.getPickUpService()));
		zipCodeSpec = (createWishlistItemZipcodeSpecification(searchDTO.getZipCode()));
		dateExpiresSpec =(createWishlistItemDateExpiresSpecification(new Date()));
		quantitySpec =(createWishlistItemQuantitySpecification(0));
		List<Specification<WishlistItem>> lst = Arrays.asList(keywordSpec,itemDespSpec,nonProfitNameSpec,distanceSpec, itemCategoriesSpec, nonProfitCategoriesSpec,
				pickUpServiceSpec, zipCodeSpec,dateExpiresSpec,quantitySpec);
		return wishlistItemRepository.findAll(this.joinSpecifications(lst), p);

		
	}

	private Specification<WishlistItem> createWishlistItemDateExpiresSpecification(final Date date) {
		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(date == null) {
					return cb.equal(cb.literal(1), cb.literal(1));
				} else {
					
					return cb.greaterThanOrEqualTo(root.get("dateExpires").as(Date.class), cb.literal(date));

				}
			}
		};
	}
	private Specification<WishlistItem> createWishlistItemQuantitySpecification(final Integer qty) {
		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.greaterThan(root.get("quantityDesired").as(Integer.class),qty);
			}
		};
	}
	private Specification<WishlistItem> createWishlistItemZipcodeSpecification(final String zipCode) {

		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(zipCode == null || zipCode.trim().isEmpty()) {
					return cb.equal(cb.literal(1), cb.literal(1));
				} else {
					
					//query.distinct(true);
					
					//CriteriaQuery<NonProfitUserLogon> user = cb.createQuery(NonProfitUserLogon.class);
					//Root<Profile> otherRoot = user.from(Profile.class);
					//Join<Profile, NonProfitUserLogon> join1 = otherRoot.join("user");
					//user.where(cb.equal(otherRoot.get("zipCode").as(String.class), cb.literal(zipCode)));
					
					log.info("testing zip code comparison");
					return cb.equal(root.get("nonProfitUserLogon").get("profile").get("zipCode").as(String.class), cb.literal(zipCode));
					
					//return cb.equal(user, root.get("nonProfitUserLogon").as(NonProfitUserLogon.class));
					//return cb.equal(cb.literal(1), cb.literal(1));
					//return cb.equal(root.get("nonProfitUserLogon").as(NonProfitUserLogon.class), sq1);
					
				}
			}
		};
	}

	private Specification<WishlistItem> createWishlistItemPickupServiceSpecification(final Boolean pickUpService) {
		// TODO Auto-generated method stub
		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(pickUpService == null || !pickUpService) {
					return cb.equal(cb.literal(1), cb.literal(1));
				}else{
					log.info("testing pickup service");					
					Join<WishlistItem, UserLogon> j1 = root.join("nonProfitUserLogon");
					Join<UserLogon, Profile> j2 = j1.join("profile");								
					return j2.get("isPickupServiceAvailable").as(String.class).in("1");
					
				}
			}
		};
	}

	private Specification<WishlistItem> createWishlistNonProfitNameSpecification(final String keyword) {
		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(keyword == null || keyword.isEmpty()) {
					return cb.equal(cb.literal(1), cb.literal(1));
				}else{									
					Join<WishlistItem, UserLogon> j1 = root.join("nonProfitUserLogon");
					Join<UserLogon, Profile> j2 = j1.join("profile");								
					return j2.get("organizationName").as(String.class).in(keyword);
					
				}
			}
		};
	}
	private Specification<WishlistItem> createWishlistItemItemDespSpecification(final String keyword) {
		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(keyword == null || keyword.isEmpty()) {
					return cb.equal(cb.literal(1), cb.literal(1));
				}
				
				return cb.like(cb.upper(root.get("notes").as(String.class)), cb.upper(cb.literal("%"+keyword+"%")));
			}
		};
	}
	private Specification<WishlistItem> createWishlistItemKeywordSpecification(final String keyword) {
		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(keyword == null || keyword.isEmpty()) {
					return cb.equal(cb.literal(1), cb.literal(1));
				}
				
				return cb.like(cb.upper(root.get("itemName").as(String.class)), cb.upper(cb.literal("%"+keyword+"%")));
			}
		};
	}

	private Specification<WishlistItem> createWishlistItemNonProfitCategoriesSpecification(
			final List<String> nonprofitCategories) {
		// TODO Auto-generated method stub
		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(nonprofitCategories == null || nonprofitCategories.isEmpty()){
					return cb.equal(cb.literal(1), cb.literal(1));
				}else{
				
				log.info("testing nonprofit category");
				//return cb.equal(root.get("nonProfitUserLogon").get("profile").get("categories").as(String.class), cb.literal(nonprofitCategories));
				Join<WishlistItem, UserLogon> j1 = root.join("nonProfitUserLogon");
				Join<UserLogon, Profile> j2 = j1.join("profile");
				Join<Profile, NonProfitCategory> j3 = j2.join("categories");				
				return j3.get("name").as(String.class).in(nonprofitCategories);
				}	
			}
		};
	}

	private Specification<WishlistItem> createWishlistItemItemCategoriesSpecification(
			final List<String> itemCategories) {

		return new Specification<WishlistItem>() {

			@Override
			public Predicate toPredicate(Root<WishlistItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(itemCategories == null || itemCategories.isEmpty())
					return cb.equal(cb.literal(1), cb.literal(1));
				
				Join<WishlistItem, ItemCategory> j = root.join("wishlistItemCategories");
				return j.get("categoryName").as(String.class).in(itemCategories);
			}
		};
	}

}