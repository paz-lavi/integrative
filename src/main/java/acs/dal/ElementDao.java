package acs.dal;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
	
	public List<ElementEntity> findAllByLocation_LatBetweenAndLocation_LngBetweenAndActive(
			@Param("minLat") double minLat, 
			@Param("maxLat") double maxLat,
			@Param("minLng") double minLng,
			@Param("maxLng") double maxLng,
			@Param("isActive") boolean isActive);	
	
	public List<ElementEntity> findAllByisActive(
			@Param("isActive") boolean isActive, 
			Pageable pageable);
	
	public List<ElementEntity> findAllByparent_elementId(
			@Param("elementId") ElementId elementId, 
			Pageable pageable);
	
	public List<ElementEntity> findAllByparent_elementIdAndActive(
			@Param("elementId") ElementId elementId, 
			@Param("isActive") boolean isActive,
			Pageable pageable);
	
	
	public List<ElementEntity> findAllByparent_elementIdAndActive(
			@Param("elementId") ElementId elementId, 
			@Param("isActive") boolean isActive);
	
	
	public Optional<ElementEntity> findAllByElementIdAndActive(
			@Param("elementId") ElementId elementId, 
			@Param("isActive") boolean isActive);
	
	
	@Query("SELECT t.parent FROM ElementEntity t WHERE t.elementId = :elementId") 
	public List<ElementEntity> findParentByElementId(
			@Param("elementId") ElementId elementId,
			Pageable pageable);
	
	@Query("SELECT t.parent FROM ElementEntity t WHERE t.elementId = :elementId and t.parent.active = :isActive")
	public List<ElementEntity> findParentByElementIdAndParent_active(
			@Param("elementId") ElementId elementId, 
			@Param("isActive") boolean isActive,
			Pageable pageable);
	

	

}
