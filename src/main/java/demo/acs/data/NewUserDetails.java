package demo.acs.data;

public class NewUserDetails {
    private String email;
    private TypeEnum role;
    private String username;
    private String avatar;


    public NewUserDetails() {

    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
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
