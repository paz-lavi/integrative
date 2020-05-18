package acs.logic;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import acs.dal.ActionDao;
import acs.data.ActionConverter;
import acs.data.ActionEntity;
import acs.data.ActionId;
import acs.data.ActionIdGenerator;
import acs.rest.boudanries.ActionBoundary;

@Service
public class ActionServiceImplementationDB implements ActionService{
	private ActionDao actionDao;
	private ActionConverter converter; 
    private String domain;

	@Autowired
	public ActionServiceImplementationDB(ActionDao actionDao, ActionConverter converter) {
		this.actionDao = actionDao;
		this.converter = converter;
	}

	 // injection of value from the spring boot configuration
  	@Value("${spring.application.name:demo}")
  	public void setProjectName(String domain) {
  		this.domain = domain;
  	}


	@Override
	@Transactional
	public Object InvokeAction(ActionBoundary action) {
		ActionId aid = new ActionId();
    	aid.setDomain(this.domain);
    	aid.setId(ActionIdGenerator.nextValue());
    	action.setActionId(aid);
    	
		if (action.getType() == null) {
			action.setType("None");
		}
		this.actionDao.save(this.converter.toEntity(action));
        return (Object)action;
	}
	@Override
	@Transactional(readOnly = true)
	public List<ActionBoundary> getAllActions(String adminDomain, String adminEmail) {
		// get entity objects from database
		Iterable<ActionEntity> all = this.actionDao
			.findAll();

		List<ActionBoundary> rv = new ArrayList<>(); 
		for (ActionEntity entity : all) {
			// map entities to boundaries
			rv.add(this.converter.fromEntity(entity));
		}
		return rv;
	}
	@Override
	@Transactional
	public void deleteAllActions(String adminDomain, String adminEmail) {
		this.actionDao.deleteAll();
		
	}

}
