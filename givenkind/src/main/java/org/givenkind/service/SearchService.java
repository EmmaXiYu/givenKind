package org.givenkind.service;

import org.givenkind.dto.SearchCriteriaDTO;
import org.givenkind.dto.SearchResultDTO;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.WishlistItem;
import org.springframework.data.domain.Page;

import java.util.List;


public interface SearchService {
	
	SearchCriteriaDTO prepareSearchPage(String userName);
	
	Page<WishlistItem> processSearchForDonor(SearchCriteriaDTO searchDTO);

	Page<DonorlistItem> processSearchForNonProfit(SearchCriteriaDTO searchDTO);
}
