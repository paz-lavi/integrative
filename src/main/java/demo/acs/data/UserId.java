package main.java.demo.acs.data;

public class UserId {
    private String domain;
    private String email;


    public UserId() {

    }

    public UserId(String domain, String email) {
        super();
        this.domain = domain;
        this.email = email;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        UserId userId = (UserId) obj;
        return this.email.equals(userId.email);
    }

}
