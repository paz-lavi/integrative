package demo.acs.data;

import org.springframework.stereotype.Component;
import demo.acs.rest.boudanries.ElementBoundary;

	@Component
	public class ElementConverter {
		
		public ElementBoundary fromEntity(ElementEntity entity) {
			ElementBoundary rv = new ElementBoundary();
			rv.setCreatedBy(entity.getCreatedBy());
			rv.setCreatedTimeStamp(entity.getCreatedTimeStamp());
			rv.setElementAttributes(entity.getElementAttributes());
			rv.setLocation(entity.getLocation());
			rv.setName(entity.getName());
			rv.setType(entity.getType());
			return rv;
		}
		

		public ElementEntity toEntity(ElementBoundary entity) {
			ElementEntity rv = new ElementEntity();
			rv.setCreatedBy(entity.getCreatedBy());
			rv.setCreatedTimeStamp(entity.getCreatedTimeStamp());
			rv.setElementAttributes(entity.getElementAttributes());
			rv.setLocation(entity.getLocation());
			rv.setName(entity.getName());
			rv.setType(entity.getType());
			return rv;
		}
}
