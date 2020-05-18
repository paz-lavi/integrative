package acs.dal;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import acs.data.UserEntity;
import acs.data.UserId;

//Create Read Update Delete - CRUD
public interface UserDao extends PagingAndSortingRepository<UserEntity, UserId>{

	Optional<UserEntity> findById(UserId userId);
	

}
