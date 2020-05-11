package acs;
import static org.assertj.core.api.Assertions.assertThat;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import acs.data.UserId;
import acs.data.UserRole;
import acs.rest.boudanries.UserBoundary;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTests {
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
		this.url = "http://localhost:" + this.port + "/acs/users";
	}
	
	@BeforeEach
	public void setup() {
		
	}
	
	@AfterEach
	public void teardown() {
		this.restTemplate
			.delete(this.url + "/deleteAll/{adminDomain}/{adminEmail}", "adminDomain", "adminEmail");
	}
	
//	@Test
//	public void testNewUserThenDataBaseContainsUserWithTheSameUserId() throws Exception{
//		// GIVEN the server is up
//		// do nothing
//		
//		// WHEN I POST new user with his id's mail attribute: "test"
//    	//UserBoundary output = 
//		UserId postedId =
//		  this.restTemplate
//			.postForObject(this.url, 
//					new UserBoundary(new UserId("aaa", "test"), UserRole.PLAYER, "aaa", "aaa"),
//					UserBoundary.class).getUserId();
//		
//		// THEN the database contains a user with the id's mail attribute "test"
//		UserId actualId = 
//					  this.restTemplate
//						.getForObject(this.url + "/login/{userDomain}/{userEmail}", 
//								UserBoundary.class, postedId.getDomain(), postedId.getEmail())
//						.getUserId();
//					
//		assertThat(actualId.getEmail())
//						.isNotNull()
//						.isEqualTo(postedId.getEmail());					
//	}
	
//	@Test
//	public void testNewUserWhithNameAndAvatarAsNaullThenDataBaseContainsUserWhithNameAndAvatarAsEmtyStrings() throws Exception{
//		// GIVEN the server is up
//	    // do nothing
//				
//		// WHEN I POST new user with name and avatar as nulls"
//		UserId postedId =
//		  this.restTemplate
//			.postForObject(this.url, 
//					new UserBoundary(new UserId("aaa", "test"), UserRole.PLAYER, null, null),
//					UserBoundary.class).getUserId();
//		
//		// THEN the database contains a user with name and avatar as empty strings
//		UserBoundary userBoundary = 
//					  this.restTemplate
//						.getForObject(this.url + "/login/{userDomain}/{userEmail}", 
//								UserBoundary.class, postedId.getDomain(), postedId.getEmail());
//					
//		assertThat(userBoundary.getUsername()).isNotNull();
//		assertThat(userBoundary.getAvatar()).isNotNull();
//	}
	
//	public void testAfterDeleteAllDataBaseIsEmpty() {
//		// GIVEN the server is up and i admin
//	    //insert new user to database
//		this.restTemplate
//			.postForObject(this.url, 
//					new UserBoundary(new UserId("aaa", "test"), UserRole.PLAYER, null, null),
//					UserBoundary.class);
//	  
//		// When i delete all users 
//		this.restTemplate
//			.delete(this.url + "/deleteAll/{adminDomain}/{adminEmail}", "adminDomain", "adminEmail");
//		 	 
//		//Then database is empty
//		UserBoundary[] users = this.restTemplate.getForObject(this.url + "/getall/{adminDomain}/{adminEmail}",
//				UserBoundary[].class, "adminDomain", "adminEmail");
//		
//		assertThat(users).isEmpty();
//	}
//	@Test
//	public void testThatUsersAreAddedToDB(){
//		restTemplate
//				.delete(this.url + "/deleteAll/{adminDomain}/{adminEmail}", "adminDomain", "adminEmail");
//
//		String domain = "domain";
//		//insert 3 new users
//		restTemplate.postForObject(this.url,
//				new UserBoundary(new UserId(domain, "test@gmail.com"), UserRole.PLAYER, "aaa", "aa"),
//				UserBoundary.class);
//		restTemplate.postForObject(this.url,
//				new UserBoundary(new UserId(domain, "test2@gmail.com"), UserRole.PLAYER, "bbb", "bb"),
//				UserBoundary.class);
//		restTemplate.postForObject(this.url,
//				new UserBoundary(new UserId(domain, "test3@gmail.com"), UserRole.PLAYER, "ccc", "cc"),
//				UserBoundary.class);
//
//		//get all users
//		UserBoundary[] users = this.restTemplate.getForObject(this.url + "/getall/{adminDomain}/{adminEmail}",
//				UserBoundary[].class, "adminDomain", "adminEmail");
//
//		assertThat(users.length).isEqualTo(3);
//
//	}


//	@Test
//	public void TestUpdatingAUserChangesTheValuesInTheDatabase(){
//		restTemplate
//				.delete(this.url + "/deleteAll/{adminDomain}/{adminEmail}", "adminDomain", "adminEmail");
//
//		String domain = "2020b.ari.kuznicki";
//		//insert new users
//		restTemplate.postForObject(this.url,
//				new UserBoundary(new UserId(domain, "test@gmail.com"), UserRole.PLAYER, "aaa", "aa"),
//				UserBoundary.class);
//
//		//update user
//		UserBoundary update = new UserBoundary(new UserId(domain, "test@gmail.com"), UserRole.PLAYER, "avfvfvfv", "fvfvfv");
//		restTemplate.put(this.url+"/"+domain+"/test@gmail.com",update, UserBoundary.class);
//
//
//		//get all users
//		UserBoundary[] users = this.restTemplate.getForObject(this.url + "/getall/{adminDomain}/{adminEmail}",
//				UserBoundary[].class, "adminDomain", "adminEmail");
//
//		assertThat(users.length).isEqualTo(1);
//		assertThat(users[0]).isEqualTo(update);
//
//	}
	
	
}
