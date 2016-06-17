package org.givenkind.repository;

import org.givenkind.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long>{
	
	State findByAbbreviation(String abbreviation);
	
	State findByName(String name);
	
}
