package demo.acs.data;

public class ElementId {
    private String domain;
    private String id;

    public ElementId() {

    }

    public ElementId(String domain, String id) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        ElementId elementId = (ElementId) obj;
        return this.id == elementId.id;
    }

}
