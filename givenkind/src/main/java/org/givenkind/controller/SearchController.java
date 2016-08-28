package org.givenkind.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.givenkind.common.UserRoleStrings;
import org.givenkind.dto.SearchCriteriaDTO;
import org.givenkind.dto.SearchResultDTO;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.ItemCategory;
import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.model.UserLogon;
import org.givenkind.model.WishlistItem;
import org.givenkind.service.ReferenceDataService;
import org.givenkind.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/search")
public class SearchController extends AbstractProfileController {
	
	private static Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Inject
	SearchService searchService;
	
	@Inject
	ReferenceDataService referenceDataService;
	
	private String generateQueryParamsForPage(SearchCriteriaDTO original, int pageNum) {
		SearchCriteriaDTO dto = new SearchCriteriaDTO();
		dto.setDistance(original.getDistance());
		dto.setItemCategories(original.getItemCategories());
		dto.setKeyword(original.getKeyword());
		dto.setNonprofitCategories(original.getNonprofitCategories());
		dto.setPageNumber(pageNum);
		dto.setPickUpService(original.getPickUpService());
		dto.setZipCode(original.getZipCode());
		return generateQueryParams(dto);
	}
	
	private String generateQueryParams(SearchCriteriaDTO dto) {
		
		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		String keyword = dto.getKeyword();
		if(keyword == null) keyword = "";
		map.add("keyword", keyword);
		int distance = Math.max(dto.getDistance(), 0);
		map.add("distance", Integer.toString(distance));
		Collection<String> itemCategories = dto.getItemCategories();
		if(itemCategories == null) itemCategories = Collections.<String>emptyList();
		for(String ic : itemCategories) {
			map.add("itemCategories", ic);
		}
		
		Collection<String> nonProfitCategories = dto.getNonprofitCategories();
		if(nonProfitCategories == null) nonProfitCategories = Collections.<String>emptyList();
		
		for(String npc : nonProfitCategories) {
			map.add("nonprofitCategories", npc);
		}
		
		int pageNumber = Math.max(dto.getPageNumber(), 0); 
		map.add("pageNumber", Integer.toString(pageNumber));
		String zipCode = dto.getZipCode();
		if(zipCode == null) zipCode = "";
		map.add("zipCode", zipCode);
		return UriComponentsBuilder.fromPath("search").queryParams(map).build().toUriString();
	}
	
	private List<String> itemCategoryNames(Collection<ItemCategory> c) {
		ArrayList<String> res = new ArrayList<String>(c.size());
		for(ItemCategory npc : c) {
			res.add(npc.getCategoryName());
		}
		return res;
	}

	private SearchResultDTO convert(DonorlistItem item) {

		Long id = item.getId();
		if(id == null) {
			id = -1l;
		}

		String itemName = item.getItemName();
		UserLogon user = item.getUser();

		List<String> itemCategories = new ArrayList<String>();
		for(ItemCategory ic : item.getItemCategories()) {
			itemCategories.add(ic.getCategoryName());
		}

		SearchResultDTO res = new SearchResultDTO();
		res.setItemCategories(itemCategories);
		res.setItemId(id);
		res.setItemName(itemName);
		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		String itemDetailsUrl = UriComponentsBuilder.fromPath("itemDetails/donor").queryParam("itemId", id).build().toUriString();
		res.setItemDetailsUrl(itemDetailsUrl);

		
		String zipCode = "";
		if(user != null && user.getProfile() != null && user.getProfile().getZipCode() != null) {
			zipCode = user.getProfile().getZipCode();
		}

		res.setZipCode(zipCode);
		return res;
	}
	
	private SearchResultDTO convert(WishlistItem item) {

		long id = item.getId();

		String itemName = item.getItemName();
	UserLogon user = item.getNonProfitUserLogon();

		List<String> itemCategories = this.itemCategoryNames(item.getWishlistItemCategories());

		SearchResultDTO res = new SearchResultDTO();
		res.setItemCategories(itemCategories);
		res.setItemId(id);
		res.setItemName(itemName);
		String itemDetailsUrl = UriComponentsBuilder.fromPath("itemDetails/nonprofit").queryParam("itemId", id).build().toUriString();
		res.setItemDetailsUrl(itemDetailsUrl);

		String zipCode = user.getProfile().getZipCode();

		res.setZipCode(zipCode);
		return res;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView launchSearchPage(@RequestParam(value = "keyword", required = false, defaultValue="") String keyword,
			@RequestParam(value = "zipCode", required = false, defaultValue="") String zipCode,
			@RequestParam(value = "distance", required = false, defaultValue="0") Integer distance,
			@RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
			@RequestParam(value = "itemCategories", required = false, defaultValue="") String[] itemCategories,
			@RequestParam(value = "nonprofitCategories", required = false, defaultValue="") String[] nonprofitCategories,
			@RequestParam(value = "pickUpService", required = false, defaultValue="false") Boolean pickUpService
	) {
	
		ModelAndView mav = new ModelAndView();
		
		
		SearchCriteriaDTO searchCriteriaDTO = new SearchCriteriaDTO(); //searchService.prepareSearchPage(userName);
		searchCriteriaDTO.setDistance(Math.max(distance, 0));
		if(itemCategories == null) itemCategories = new String[0];
		searchCriteriaDTO.setItemCategories(Arrays.asList(itemCategories));
		if(keyword == null) keyword = "";
		searchCriteriaDTO.setKeyword(keyword);
		if(nonprofitCategories == null) nonprofitCategories = new String[0];
		searchCriteriaDTO.setNonprofitCategories(Arrays.asList(nonprofitCategories));
		searchCriteriaDTO.setPageNumber(Math.max(pageNumber, 0));
		searchCriteriaDTO.setPickUpService(pickUpService);
		if(zipCode == null) zipCode = "";
		searchCriteriaDTO.setZipCode(zipCode);
		
		mav.addObject("searchCriteriaDTO", searchCriteriaDTO);
		
		List<SearchResultDTO> searchResults = new ArrayList<SearchResultDTO>();
		boolean hasPreviousPage = false;
		boolean hasNextPage = false;
		
		if(this.isUserANonProfit()) {
			Page<DonorlistItem> pagedSearchResults = searchService.processSearchForNonProfit(searchCriteriaDTO);
			hasPreviousPage = pagedSearchResults.hasPrevious();
			hasNextPage = pagedSearchResults.hasNext();
			for(DonorlistItem i : pagedSearchResults) {
				searchResults.add(convert(i));
			}
		} else {
			Page<WishlistItem> pagedSearchResults = searchService.processSearchForDonor(searchCriteriaDTO);
			hasPreviousPage = pagedSearchResults.hasPrevious();
			hasNextPage = pagedSearchResults.hasNext();
			for(WishlistItem i : pagedSearchResults) {
				searchResults.add(convert(i));
			}
		}
		
		mav.addObject("searchResults", searchResults);
		mav.setViewName("search");
		mav.addObject("hasPreviousPage", hasPreviousPage);
		mav.addObject("previousPageQueryParams", generateQueryParamsForPage(searchCriteriaDTO, pageNumber-1));
		mav.addObject("hasNextPage", hasNextPage);
		mav.addObject("nextPageQueryParams", generateQueryParamsForPage(searchCriteriaDTO, pageNumber+1));
		mav.addObject("nonprofitCategories", this.populateNonprofitCategories());
		mav.addObject("itemCategories", this.populateItemCategories());
		
		mav.addObject("userId" , this.getMyUserId());
		
		log.info("search info is "+generateQueryParams(searchCriteriaDTO));
		
		log.info("search criteria is "+searchCriteriaDTO);
		
		return mav;
	}
	
	private boolean isUserANonProfit() {
		SecurityContext context = SecurityContextHolder.getContext();
		
		if (context == null) {
			log.info("user is not a non-profit");
			return false;
		}
		
		Authentication authentication = context.getAuthentication();
		if(authentication == null) {
			log.info("user is not a non-profit");
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		if (authorities == null || authorities.isEmpty()) {
			log.info("user is not a non-profit");
			return false;
		}
		
		
		
		for(GrantedAuthority ga : authorities) {
			if(ga.getAuthority().equalsIgnoreCase(UserRoleStrings.ROLE_NONPROFIT)) {
				log.info("user is a non-profit");
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView getSearchResults(@ModelAttribute("searchCriteriaDTO") SearchCriteriaDTO searchCriteriaDTO,
			BindingResult result, SessionStatus status, Model model) {
		ModelAndView mav = new ModelAndView();
		
		searchCriteriaDTO.setDistance(Math.max(searchCriteriaDTO.getDistance(), 0));
		if(searchCriteriaDTO.getItemCategories() == null)
			searchCriteriaDTO.setItemCategories(Collections.<String>emptyList());
		if(searchCriteriaDTO.getKeyword() == null)
			searchCriteriaDTO.setKeyword("");
		if(searchCriteriaDTO.getNonprofitCategories() == null)
			searchCriteriaDTO.setNonprofitCategories(Collections.<String>emptyList());
		searchCriteriaDTO.setPageNumber(Math.max(searchCriteriaDTO.getPageNumber(), 0));
		
		if(searchCriteriaDTO.getZipCode() == null)
			searchCriteriaDTO.setZipCode("");
		
		mav.addObject("searchCriteriaDTO", searchCriteriaDTO);
		
		List<SearchResultDTO> searchResults = new ArrayList<SearchResultDTO>();
		boolean hasPreviousPage = false;
		boolean hasNextPage = false;
		
		if(this.isUserANonProfit()) {
			Page<DonorlistItem> pagedSearchResults = searchService.processSearchForNonProfit(searchCriteriaDTO);
			hasPreviousPage = pagedSearchResults.hasPrevious();
			hasNextPage = pagedSearchResults.hasNext();
			for(DonorlistItem i : pagedSearchResults) {
				searchResults.add(convert(i));
			}
		} else {
			Page<WishlistItem> pagedSearchResults = searchService.processSearchForDonor(searchCriteriaDTO);
			hasPreviousPage = pagedSearchResults.hasPrevious();
			hasNextPage = pagedSearchResults.hasNext();
			for(WishlistItem i : pagedSearchResults) {
				searchResults.add(convert(i));
			}
		}
		
		int pageNumber = searchCriteriaDTO.getPageNumber();
		
		mav.addObject("searchResults", searchResults);
		mav.setViewName("search");
		mav.addObject("hasPreviousPage", hasPreviousPage);
		mav.addObject("previousPageQueryParams", generateQueryParamsForPage(searchCriteriaDTO, pageNumber-1));
		mav.addObject("hasNextPage", hasNextPage);
		mav.addObject("nextPageQueryParams", generateQueryParamsForPage(searchCriteriaDTO, pageNumber+1));
		mav.addObject("nonprofitCategories", this.populateNonprofitCategories());
		mav.addObject("itemCategories", this.populateItemCategories());
		
		mav.addObject("userId" , this.getMyUserId());
		
		log.info("search info is "+generateQueryParams(searchCriteriaDTO));
		
		return mav;
	}
	
	public List<String> populateNonprofitCategories() {
		return referenceDataService.getNonprofitCategoryList();
	}
	
	public List<String> populateItemCategories() {
		return referenceDataService.getItemCategoryList();
	}
}
