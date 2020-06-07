package acs.dal;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import acs.data.MessageEntity;
import acs.data.UserId;

public interface MessageDao extends PagingAndSortingRepository<MessageEntity, Integer>{
	
	public List<MessageEntity> findAllByInvokedBy(
			@Param("user_Id") UserId user_Id, 
			Pageable pageable);
	
	public Page<MessageEntity> findAll(
			Pageable pageable);
	
	public List<MessageEntity> findAllByTreated(
			@Param("treated") boolean treated,
			Pageable pageable);
	
	public List<MessageEntity> findAllByInvokedByAndTreated(
			@Param("user_Id") UserId user_Id, 
			@Param("treated") boolean treated,
			Pageable pageable);
	
	

}
