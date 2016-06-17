package givenkind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.givenkind.common.UserRoleStrings;
import org.givenkind.config.WebAppConfig;
import org.givenkind.model.ItemCategory;
import org.givenkind.model.NonProfitCategory;
import org.givenkind.model.State;
import org.givenkind.model.StatusCategory;
import org.givenkind.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfig.class)
@ActiveProfiles("dev")
public class DataPopulation {

	@Autowired
	org.givenkind.repository.StateRepository sr;

	@Autowired
	org.givenkind.repository.NonprofitCategoryRepository npcr;

	@Autowired
	org.givenkind.repository.UserRoleRepository urr;

	@Autowired
	org.givenkind.repository.ItemCategoryRepository icr;
	
	@Autowired
	org.givenkind.repository.StatusCategoryRepository scr;
	
	@Transactional
	@Rollback(false)
	@Test
	public void insertUserRoles() {
		if (urr.count() == 0) {

			for (String s : Arrays.asList(UserRoleStrings.ROLE_ADMIN, UserRoleStrings.ROLE_NONPROFIT,
					UserRoleStrings.ROLE_DONOR, UserRoleStrings.ROLE_VOLUNTEER)) {
				UserRole ur = new UserRole();
				ur.setName(s);
				ur.setDescription("N/A");
				urr.save(ur);
				
			}

			System.err.println("added user roles");
		} else {
			System.err.println("user roles already existed");
		}
	}

	@Transactional
	@Rollback(false)
	@Test
	public void insertStates() {
		if (sr.count() == 0) {
			HashMap<String, String> values = new HashMap<String, String>();
			values.put("AL", "Alabama");
			values.put("AK", "Alaska");
			values.put("AZ", "Arizona");
			values.put("AR", "Arkansas");
			values.put("CA", "California");
			values.put("CO", "Colorado");
			values.put("CT", "Connecticut");
			values.put("DE", "Delaware");
			values.put("FL", "Florida");
			values.put("GA", "Georgia");
			values.put("HI", "Hawaii");
			values.put("ID", "Idaho");
			values.put("IL", "Illinois");
			values.put("IN", "Indiana");
			values.put("IA", "Iowa");
			values.put("KS", "Kansas");
			values.put("KY", "Kentucky");
			values.put("LA", "Louisiana");
			values.put("ME", "Maine");
			values.put("MD", "Maryland");
			values.put("MA", "Massachusetts");
			values.put("MI", "Michigan");
			values.put("MN", "Minnesota");
			values.put("MS", "Mississippi");
			values.put("MO", "Missouri");
			values.put("MT", "Montana");
			values.put("NE", "Nebraska");
			values.put("NV", "Nevada");
			values.put("NH", "New Hampshire");
			values.put("NJ", "New Jersey");
			values.put("NM", "New Mexico");
			values.put("NY", "New York");
			values.put("NC", "North Carolina");
			values.put("ND", "North Dakota");
			values.put("OH", "Ohio");
			values.put("OK", "Oklahoma");
			values.put("OR", "Oregon");
			values.put("PA", "Pennsylvania");
			values.put("RI", "Rhode Island");
			values.put("SC", "South Carolina");
			values.put("SD", "South Dakota");
			values.put("TN", "Tennessee");
			values.put("TX", "Texas");
			values.put("UT", "Utah");
			values.put("VT", "Vermont");
			values.put("VA", "Virginia");
			values.put("WA", "Washington");
			values.put("WV", "West Virginia");
			values.put("WI", "Wisconsin");
			values.put("WY", "Wyoming");

			for (Entry<String, String> entry : values.entrySet()) {
				State s = new State();
				s.setName(entry.getValue());
				s.setAbbreviation(entry.getKey());
				sr.save(s);
			}

			System.err.println("states added");
		} else {
			System.err.println("states already existed");
		}
	}

	@Transactional
	@Rollback(false)
	@Test
	public void insertItemCategories() {
		if (icr.count() == 0) {
			for (String s : Arrays.asList("Airline Miles and Hotel Points", "Animal/Wildlife", "Appliances",
					"Arts and Craft Materials", "Baby Items", "Books", "Building Materials", "Clothing-Adults",
					"Clothing-Children", "Decor", "Electronics", "Food", "Furniture-Office", "Furniture-Residential",
					"Kitchen", "Linens/Bedding", "Medical Supplies and Equipment", "Monetary Donation", "Music",
					"Office Space", "Office Supplies and Equipment", "Other", "Personal Care", "Sports", "Storage",
					"Towels")) {
				ItemCategory ic = new ItemCategory();
				ic.setCategoryName(s);
				ic.setDescription("N/A");
				icr.save(ic);
			}

			System.err.println("saved item categories");
		} else {
			System.err.println("item categories already there");
		}
	}

	@Transactional
	@Rollback(false)
	@Test
	public void insertNonProfitCategories() {
		if (npcr.count() == 0) {

			HashMap<String, String> values = new HashMap<String, String>();

			values.put("Animals", "N/A");
			values.put("Arts", "N/A");
			values.put("Children", "N/A");
			values.put("Education", "N/A");
			values.put("Environment", "N/A");
			values.put("Health", "N/A");
			values.put("Human Services", "N/A");
			values.put("International Benefit", "N/A");
			values.put("Public Benefit", "N/A");
			values.put("Religious/Spiritual", "N/A");
			values.put("Other", "N/A");

			for (Entry<String, String> entry : values.entrySet()) {
				NonProfitCategory npc = new NonProfitCategory();
				npc.setDescription(entry.getValue());
				npc.setName(entry.getKey());
				npcr.save(npc);
			}
			System.err.println("saved values");
		} else {
			System.err.println("values already existed");
		}
	}
	
	@Transactional
	@Rollback(false)
	@Test
	public void insertStatusCategories(){
		if(scr.count() == 0) {
			
			for(String s : Arrays.asList("Donor Requested", "Nonprofit Requested",
					"Accepted", "In Transit", "Cancelled")) {
				StatusCategory sc = new StatusCategory();
				sc.setStatusCategoryName(s);
				scr.save(sc);
			}
			
			System.out.println("added transaction statuses");
		} else {
			System.err.println("transaction statuses already exist");
		}
	}

	@Test
	public void test() {
		String s = Arrays.toString(sr.findAll().toArray());
		System.out.println(s);
	}

}
