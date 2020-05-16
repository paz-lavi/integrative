package acs.dal;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import acs.data.ElementEntity;
import acs.data.ElementId;
import acs.data.Location;


public interface ElementDao extends PagingAndSortingRepository<ElementEntity, ElementId>{

	public List<ElementEntity> findAllByElementId(
			@Param("element_Id") ElementId element_Id, 
			Pageable pageable);

	public List<ElementEntity> findAllByName(
			@Param("name") String name, 
			Pageable pageable);	
	
	public List<ElementEntity> findAllByType(
			@Param("type") String type, 
			Pageable pageable);	
	
	public List<ElementEntity> findAllByLocation(
			@Param("location") Location location, 
			Pageable pageable);	
}
