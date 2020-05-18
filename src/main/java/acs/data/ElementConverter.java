package acs.data;

import acs.rest.boudanries.ElementBoundary;
import org.springframework.stereotype.Component;

	@Component
	public class ElementConverter {
		public ElementBoundary fromEntity(ElementEntity entity) {
			ElementBoundary rv = new ElementBoundary();
			rv.setElementId(entity.getElementId());
			rv.setType(entity.getType());
			rv.setName(entity.getName());
			rv.setIsActive(entity.getActive());
			rv.setCreatedBy(entity.getCreatedBy());
			rv.setCreatedTimestamp(entity.getCreatedTimestamp());
			rv.setElementAttributes(entity.getElementAttributes());
			rv.setLocation(entity.getLocation());
			return rv;
		}
		

		public ElementEntity toEntity(ElementBoundary entity) {
			ElementEntity rv = new ElementEntity();
			rv.setElementId(entity.getElementId());
			rv.setType(entity.getType());
			rv.setName(entity.getName());
			rv.setActive(entity.isActive());
			rv.setCreatedBy(entity.getCreatedBy());
			rv.setCreatedTimestamp(entity.getCreatedTimestamp());
			rv.setElementAttributes(entity.getElementAttributes());
			rv.setLocation(entity.getLocation());
			return rv;
		}
}
