package model;

public class Supplier {
    private String id;
    private String name;
    private String contact;
    private String email;
    private String phone;
    
    public Supplier() {}
    
    public Supplier(String id, String name, String contact, String email, String phone) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.phone = phone;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Contact: %s, Email: %s, Phone: %s",
                id, name, contact, email, phone);
    }
    
    public String toCSV() {
        return String.join(",",
                id, name, contact, email, phone);
    }
}
