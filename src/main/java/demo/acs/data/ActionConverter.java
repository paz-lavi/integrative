package demo.acs.data;

import demo.acs.rest.boudanries.ActionBoundary;
import org.springframework.stereotype.Component;

@Component
public class ActionConverter {
    public ActionBoundary fromEntity(ActionEntity entity) {
        ActionBoundary rv = new ActionBoundary();
        rv.setActionAttributes(entity.getActionAttributes());
        rv.setActionId(entity.getActionId());
        rv.setCreatedTimeStamp(entity.getCreatedTimeStamp());
        rv.setElement(entity.getElement());
        rv.setInvokedBy(entity.getInvokedBy());
        rv.setType(entity.getType());
        return rv;
    }

    public ActionEntity toEntity(ActionBoundary boundary) {
        ActionEntity rv = new ActionEntity();
        rv.setActionAttributes(boundary.getActionAttributes());
        rv.setActionId(boundary.getActionId());
        rv.setCreatedTimeStamp(boundary.getCreatedTimeStamp());
        rv.setElement(boundary.getElement());
        rv.setInvokedBy(boundary.getInvokedBy());
        rv.setType(boundary.getType());
        return rv;
    }

}
