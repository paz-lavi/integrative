package test.java.demo;
import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import main.java.demo.acs.data.UserId;
import main.java.demo.acs.data.UserRole;
import main.java.demo.acs.rest.boudanries.UserBoundary;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTests {
	private RestTemplate restTemplate;
	private String url;
	private int port;
	
	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void init() {
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:" + this.port + "/acs/users";
	}
	
	@BeforeEach
	public void setup() {
		
	}
	
	@AfterEach
	public void teardown() {
		this.restTemplate
			.delete(this.url);
	}
	
	@Test
	public void testContext() {
		
	}
	
	@Test
	public void testNewUserThenDataBaseContainsUserWithTheSameUserId() throws Exception{
		// GIVEN the server is up
		// do nothing
		
		// WHEN I POST new user with his id's mail attribute: "test"
    	//UserBoundary output = 
		UserId postedId =
		  this.restTemplate
			.postForObject(this.url, 
					new UserBoundary(new UserId("aaa", "test"), UserRole.PLAYER, "aaa", "aaa"),
					UserBoundary.class).getUserId();
		
		// THEN the database contains a user with the id's mail attribute "test"
			UserId actualId = 
					  this.restTemplate
						.getForObject(this.url + "/login/{userDomain}/{userEmail}", UserBoundary.class, postedId)
						.getUserId();
					
					assertThat(actualId.getEmail())
						.isNotNull()
						.isEqualTo(postedId.getEmail());
	}
	
}
