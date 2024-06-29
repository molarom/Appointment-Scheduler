package domain.stores.Contact;

import domain.database.annotations.Column;
import domain.database.annotations.Id;

/**
 * Contact represents a contact document stored within the database.
 *
 * @author Brandon Epperson
 */
public class Contact {
    @Id
    @Column(name = "contact_id")
    private int contact_id;

    @Column(name = "contact_name")
    private String contact_name;

    @Column(name = "email")
    private String email;

    /**
     * Default constructor to initialize an empty Contact
     */
    public Contact() {
    }

    /**
     * Constructs a new Contact with the provided id.
     *
     * @param contact_id the contact's id
     */
    public Contact(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * @return the contact id
     */
    public int getContactId() {
        return contact_id;
    }

    /**
     * @param contact_id the contact id to set
     */
    public void setContactId(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * @return the contact name
     */
    public String getContactName() {
        return contact_name;
    }

    /**
     * @param contact_name the contact name to set
     */
    public void setContactName(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contact_id=" + contact_id +
                ", contact_name='" + contact_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
