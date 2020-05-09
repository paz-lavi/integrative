package acs.dal;

import org.springframework.data.repository.PagingAndSortingRepository;
import acs.data.ElementEntity;
import acs.data.ElementId;

//Create Read Update Delete - CRUD
public interface ElementDao extends PagingAndSortingRepository<ElementEntity, ElementId>{
	
}
