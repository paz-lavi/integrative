package acs.dal;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import acs.data.ElementEntity;
import acs.data.ElementId;



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
	
	public List<ElementEntity> findAllByLocation_LatBetweenAndLocation_LngBetween(
			@Param("minLat") double minLat, 
			@Param("maxLat") double maxLat,
			@Param("minLng") double minLng,
			@Param("maxLng") double maxLng,
			Pageable pageable);	
	
	public List<ElementEntity> findAllByisActive(
			@Param("isActive") boolean isActive, 
			Pageable pageable);

}
