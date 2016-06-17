/**
 * 
 */
package org.givenkind.service;

import org.givenkind.dto.DonorlistDTO;
import java.util.List;
/**
 * @author force
 *
 */
public interface DonorlistService {

	List<DonorlistDTO> getListOfDonatedItems(Long userId);
	
	void deleteDonatedItem(Long id);
	
	void addDonatedItem(DonorlistDTO donorlistDTO);
	
	void editDonatedItem(Long id, DonorlistDTO editedItem);
	
	DonorlistDTO getItemById(Long id);
}
