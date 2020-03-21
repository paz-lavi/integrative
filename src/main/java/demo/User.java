package demo;


public class User  {
	
	private UserId userId;
	private String role;
	private String username;
	private String avatar;
	
	
	public User() {
		
	}

	public User(UserId userId, String role, String username, String avatar) {
		super();
		this.userId = userId;
		this.role = role;
		this.username = username;
		this.avatar = avatar;
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public boolean equals(Object obj) {	
		User user = (User)obj;
		return this.userId.equals(user.userId);
	}
	
	
}
