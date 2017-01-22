package org.givenkind.repository;

import java.util.List;

import org.givenkind.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
	public Profile findById(Long Id);
	//public Profile findByEIN(Long EIN);
	
  //public Profile findByProfile(String contactEmail);
	
	public List<Profile> findAll();

	public Profile findByEin(String ein);
	
	public Profile findByContactEmail(String email);
}
