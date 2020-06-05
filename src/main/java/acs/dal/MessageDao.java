package acs.dal;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import acs.data.ElementEntity;
import acs.data.ElementId;
import acs.data.MessageEntity;

public interface MessageDao extends PagingAndSortingRepository<MessageEntity, Integer>{
	
	public List<MessageEntity> findAllByInvokedBy(
			@Param("element_Id") ElementId element_Id, 
			Pageable pageable);
	
	
	

}
