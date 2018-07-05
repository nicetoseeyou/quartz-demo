package lab.nice.demo.quartz.entity;


public class Contacts {
    private Integer contactId;
    private String lastName;
    private String firstName;

    public Contacts() {
        super();
    }

    public Contacts(Integer contactId, String lastName, String firstName) {
        this.contactId = contactId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacts contacts = (Contacts) o;

        if (contactId != null ? !contactId.equals(contacts.contactId) : contacts.contactId != null) return false;
        if (lastName != null ? !lastName.equals(contacts.lastName) : contacts.lastName != null) return false;
        return firstName != null ? firstName.equals(contacts.firstName) : contacts.firstName == null;
    }

    @Override
    public int hashCode() {
        int result = contactId != null ? contactId.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contactId=" + contactId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
