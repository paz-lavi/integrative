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
			rv.setIsActive(entity.isActive());
			rv.setCreatedBy(entity.getCreatedBy());
			rv.setCreatedTimeStamp(entity.getCreatedTimeStamp());
			rv.setElementAttributes(entity.getElementAttributes());
			rv.setLocation(entity.getLocation());
			return rv;
		}
		

		public ElementEntity toEntity(ElementBoundary entity) {
			ElementEntity rv = new ElementEntity();
			rv.setElementId(entity.getElementId());
			rv.setType(entity.getType());
			rv.setName(entity.getName());
			rv.setIsActive(entity.isActive());
			rv.setCreatedBy(entity.getCreatedBy());
			rv.setCreatedTimeStamp(entity.getCreatedTimeStamp());
			rv.setElementAttributes(entity.getElementAttributes());
			rv.setLocation(entity.getLocation());
			return rv;
		}
}
