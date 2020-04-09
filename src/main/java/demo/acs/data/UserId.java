package main.java.demo.acs.data;

public class UserId implements Comparable{
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

	@Override
	public int compareTo(Object o) {
		UserId id = (UserId)o;
		return this.getEmail().compareTo(id.getEmail());
	}

	@Override
	public String toString() {
		return "UserId [domain=" + domain + ", email=" + email + "]";
	}
	
	

}
