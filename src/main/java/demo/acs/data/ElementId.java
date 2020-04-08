package main.java.demo.acs.data;

public class ElementId {
    private String domain;
    private int id;

    public ElementId() {

    }

    public ElementId(String domain, int id) {
        super();
        this.domain = domain;
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        ElementId elementId = (ElementId) obj;
        return this.id == elementId.id;
    }

}
