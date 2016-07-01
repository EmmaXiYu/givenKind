package org.givenkind.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.givenkind.model.NonProfitCategory;
import org.givenkind.model.State;
import org.givenkind.model.ItemCategory;
import org.givenkind.repository.NonprofitCategoryRepository;
import org.givenkind.repository.StateRepository;
import org.givenkind.repository.ItemCategoryRepository;
import org.givenkind.service.ReferenceDataService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReferenceDataServiceImpl implements ReferenceDataService {

	@Inject
	NonprofitCategoryRepository nonprofitCategoryRepository;
	
	@Inject
	StateRepository stateRepository;
	
	@Inject
	ItemCategoryRepository ItemCategoryRepository;
	
	@Override
	public List<String> getStateList() {
		
		List<String> stateList = new ArrayList<String>();
		List<State> states = stateRepository.findAll(sortByAbbreviationAsc());
		stateList.add("");
		for (State state : states) {
			stateList.add(state.getAbbreviation());
		}
		return stateList;
	}

	@Override
	public List<String> getNonprofitCategoryList() {
		List<String> nonprofitCategoryList = new ArrayList<String>();
		
		
		if(this.nonprofitCategoryRepository.count() == 0) {
			NonProfitCategory c1 = new NonProfitCategory();
			c1.setDescription("category desc");
			c1.setName("category 1");
			this.nonprofitCategoryRepository.save(c1);
		}
		List<NonProfitCategory> nonprofitCategories = nonprofitCategoryRepository.findAll(sortByIdAsc());
		
		nonprofitCategoryList.add("");
		for (NonProfitCategory nonprofitCategory : nonprofitCategories) {
			nonprofitCategoryList.add(nonprofitCategory.getName());
		}
		return nonprofitCategoryList;
	}
	
	@Override
	public List<String> getItemCategoryList() {
		List<String> itemCategoryList = new ArrayList<String>();
		List<ItemCategory> itemCategories = ItemCategoryRepository.findAll(sortByIdAsc());
		//wishlistItemCategoryList.add("");
		for (ItemCategory itemCategory : itemCategories) {
			itemCategoryList.add(itemCategory.getCategoryName());
		}
		return itemCategoryList;
	}
	
	private Sort sortByAbbreviationAsc() {
		return new Sort(Sort.Direction.ASC, "abbreviation");
	}
	
	private Sort sortByIdAsc() {
		return new Sort(Sort.Direction.ASC, "id");
	}
	
	

}
