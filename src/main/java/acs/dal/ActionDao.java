package acs.dal;

import org.springframework.data.repository.CrudRepository;

import acs.data.ActionEntity;
import acs.data.ActionId;

//Create Read Update Delete - CRUD
public interface ActionDao extends CrudRepository<ActionEntity, ActionId>{

}
