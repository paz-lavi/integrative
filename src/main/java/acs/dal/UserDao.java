package acs.dal;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import acs.data.UserEntity;
import acs.data.UserId;

//Create Read Update Delete - CRUD
public interface UserDao extends CrudRepository<UserEntity, UserId>{

	Optional<UserEntity> findById(UserId userId);
	

}
