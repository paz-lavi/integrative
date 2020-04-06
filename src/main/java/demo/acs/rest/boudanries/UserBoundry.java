package demo.acs.rest.boudanries;


import demo.acs.data.TypeEnum;
import demo.acs.data.UserId;

public class UserBoundry {
    private UserId userId;
    private TypeEnum role;
    private String username;
    private String avatar;


    public UserBoundry() {

    }

    public UserBoundry(UserId userId, TypeEnum role, String username, String avatar) {
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

    public TypeEnum getRole() {
        return role;
    }

    public void setRole(TypeEnum role) {
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


}
