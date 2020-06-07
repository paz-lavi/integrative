package acs.data;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;



@Entity
@Table(name="USERS")
public class UserEntity {
	private UserId userId;
	private UserRole role;
	private String username;
	private String avatar;


	public UserEntity() {
    }
	
	
	@EmbeddedId
    public UserId getUserId() {
    return userId;
    }

	    public void setUserId(UserId userId) {
	        this.userId = userId;
	    }
        
	    @Enumerated(EnumType.STRING)
	    public UserRole getRole() {
	        return role;
	    }

	    public void setRole(UserRole role) {
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
		public String toString() {
			return "UserEntity [userId=" + userId + ", role=" + role + ", username=" + username + ", avatar=" + avatar
					+ "]";
		}
	    
	}
