package domain;

import domain.database.annotations.Column;
import domain.database.annotations.Id;
import domain.time.Time;

/**
 * Appointment represents an appointment object for use in the application.
 *
 * @author Brandon Epperson
 */
public class Appointment {
    @Id
    @Column(name = "appointment_id")
    private int appointment_id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "start")
    private Time start;

    @Column(name = "end")
    private Time end;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "last_updated_by")
    private String last_updated_by;

    @Column(name = "create_date")
    private Time create_date;

    @Column(name = "last_update")
    private Time last_update;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "contact_id")
    private int contact_id;

    /**
     * Default constructor to initialize an empty Appointment
     */
    public Appointment() {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", created_by='" + created_by + '\'' +
                ", last_updated_by='" + last_updated_by + '\'' +
                ", create_date=" + create_date +
                ", last_update=" + last_update +
                ", customer_id=" + customer_id +
                ", user_id=" + user_id +
                ", contact_id=" + contact_id +
                '}';
    }
}
