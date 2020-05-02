package acs.dal;

import org.springframework.data.repository.CrudRepository;

import acs.data.ElementEntity;

//Create Read Update Delete - CRUD
public interface ElementDao extends CrudRepository<ElementEntity, String>{
}
