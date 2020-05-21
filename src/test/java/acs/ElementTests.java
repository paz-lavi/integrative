package acs;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import acs.data.ElementEntity;
import acs.data.ElementId;
import acs.data.NewUserDetails;
import acs.data.UserId;
import acs.data.UserRole;
import acs.rest.boudanries.ElementBoundary;
import acs.rest.boudanries.UserBoundary;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ElementTests {
	private RestTemplate restTemplate;
	private String url;
	private int port;
	
	@Test
	public void testContext() {
		
	}
	
	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void init() {
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:" + this.port + "/acs/elements/";
	}
	
	@BeforeEach
	public void setup() {
		
	}
	
	@AfterEach 
	public void teardown() {
		this.restTemplate
			.delete(this.url + "deleteAll/adminDomain/adminEmail");
	}
	
	
	@Test
	public void test_create_new_element_and_then_element_in_database() throws Exception{ 
		// GIVEN the server is up and system contains manager
		NewUserDetails userDitails = new NewUserDetails();
		userDitails.setAvatar(":-");
		userDitails.setEmail("aaa@ff.ff");
		userDitails.setRole(UserRole.MANAGER);
		userDitails.setUsername("aaa");
		UserId userId = this.restTemplate.postForObject("http://localhost:" + this.port + "/acs/users", 
				userDitails, 
				UserBoundary.class).getUserId(); 
		System.out.println(userId.toString()+"user");  

		// WHEN I POST new element by manager"
		ElementBoundary elementBoundary = new ElementBoundary();
		ElementId elementId = new ElementId();
		elementId.setDomain(userId.getDomain());
		elementBoundary.setElementId(elementId);
		elementBoundary.setCreatedBy(userId);
		elementBoundary.setIsActive(true);
	
		ElementId putElementId = this.restTemplate
			.postForObject(this.url + userId.getDomain() + "/" + userId.getEmail(), 
					elementBoundary, 
					ElementBoundary.class).getElementId();
		
		// THEN the database contains a user with the id's mail attribute "test"
		ElementId retriveElementId = this.restTemplate
				.getForObject(this.url + userId.getDomain() + "/" + userId.getEmail() +
						"/" + putElementId.getDomain() + "/" + putElementId.getId(),
						ElementBoundary.class).getElementId();
		
		assertThat(retriveElementId)
		.isNotNull();
	}
	
	

	@Test
	public void test_create_new_element_if_user_is_player_and_fail() throws Exception{
		// GIVEN the server is up and system contains player
		NewUserDetails userDitails = new NewUserDetails();
		userDitails.setAvatar(":-");
		userDitails.setEmail("aaa@ff.ff");
		userDitails.setRole(UserRole.PLAYER);
		userDitails.setUsername("aaa");
		UserId userId = this.restTemplate.postForObject("http://localhost:" + this.port + "/acs/users", 
				userDitails, 
				UserBoundary.class).getUserId();

		// WHEN I POST new element by player"
		ElementBoundary elementBoundary = new ElementBoundary();
		ElementId elementId = new ElementId();
		elementId.setDomain(userId.getDomain());
		elementBoundary.setElementId(elementId);
		elementBoundary.setCreatedBy(userId);
		elementBoundary.setIsActive(true);
	
		// Then throw exception
		assertThrows(Exception.class, ()-> this.restTemplate
			.postForObject(this.url + userId.getDomain() + "/" + userId.getEmail(), 
					elementBoundary, 
					ElementBoundary.class).getElementId());
	}
	
	
	@Test
	public void test_update_new_element_if_user_is_player_and_fail() throws Exception{
		// GIVEN the server is up and system contains player and element
		NewUserDetails userDitails = new NewUserDetails();
		userDitails.setAvatar(":-");
		userDitails.setEmail("aaa@ff.ff");
		userDitails.setRole(UserRole.PLAYER);
		userDitails.setUsername("aaa");
		UserId userIdPlayer= this.restTemplate.postForObject("http://localhost:" + this.port + "/acs/users", 
				userDitails, 
				UserBoundary.class).getUserId();
		
		userDitails.setAvatar(":-");
		userDitails.setEmail("aaa@ff.ff");
		userDitails.setRole(UserRole.MANAGER);
		userDitails.setUsername("aaa");
		UserId userIdManager = this.restTemplate.postForObject("http://localhost:" + this.port + "/acs/users", 
				userDitails, 
				UserBoundary.class).getUserId();
		
		ElementBoundary elementBoundary = new ElementBoundary();
		ElementId elementId = new ElementId();
		elementId.setDomain(userIdManager.getDomain());
		elementId.setId("111");
		elementBoundary.setElementId(elementId);
		elementBoundary.setCreatedBy(userIdManager);
		elementBoundary.setIsActive(true);	
		ElementBoundary postedElement = this.restTemplate
			.postForObject(this.url + userIdManager.getDomain() + "/" + userIdManager.getEmail(), 
					elementBoundary, 
					ElementBoundary.class);

		// WHEN I PUT element by player"
		// Then throw exception
		assertThrows(Exception.class, ()-> this.restTemplate
				.put(url + userIdPlayer.getDomain() + "/" + userIdPlayer.getEmail() +
						"/" + elementId.getDomain() + "/" + elementId.getId(),
						postedElement));

	}
	
	
	@Test
	public void test_update_new_element_if_user_is_manager_and_element_in_DB() throws Exception{
		// GIVEN the server is up and system contains manager and element
		NewUserDetails userDitails = new NewUserDetails();
		userDitails.setAvatar(":-");
		userDitails.setEmail("aaa@ff.ff");
		userDitails.setRole(UserRole.MANAGER);
		userDitails.setUsername("aaa");
		UserId userIdManager = this.restTemplate.postForObject("http://localhost:" + this.port + "/acs/users", 
				userDitails, 
				UserBoundary.class).getUserId();
		
		ElementBoundary elementBoundaryPosted = new ElementBoundary();
		ElementId postedElementId = new ElementId();
		postedElementId.setDomain(userIdManager.getDomain());
		postedElementId.setId("111");
		elementBoundaryPosted.setElementId(postedElementId);
		elementBoundaryPosted.setCreatedBy(userIdManager);
		elementBoundaryPosted.setIsActive(true);	
		ElementId elementIdDB = this.restTemplate
			.postForObject(this.url + userIdManager.getDomain() + "/" + userIdManager.getEmail(), 
					elementBoundaryPosted, 
					ElementBoundary.class).getElementId();
		

		// WHEN I PUT element by manager"
		ElementBoundary elementBoundaryPut = new ElementBoundary();
		elementBoundaryPut.setElementId(elementIdDB);
		elementBoundaryPut.setCreatedBy(userIdManager);
		elementBoundaryPut.setName("ggg");
		
		this.restTemplate
				.put(this.url + userIdManager.getDomain() + "/" + userIdManager.getEmail() +
						"/" + elementIdDB.getDomain() + "/" + elementIdDB.getId(),
						elementBoundaryPut);
		// THEN the database contains a user with the id's mail attribute "test"
		ElementBoundary  retriveElement = this.restTemplate
				.getForObject(this.url + userIdManager.getDomain() + "/" + userIdManager.getEmail() +
						"/" + elementIdDB.getDomain() + "/" + elementIdDB.getId(),
						ElementBoundary.class);

		assertThat(retriveElement.getName())
		.isNotNull()
		.isEqualTo("ggg");
	}

}
