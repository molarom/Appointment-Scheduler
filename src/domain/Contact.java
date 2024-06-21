package domain;

import domain.database.annotations.Column;
import domain.database.annotations.Id;

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
}
