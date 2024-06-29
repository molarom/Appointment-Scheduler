package domain.stores.Appointment;

import domain.database.annotations.Column;
import domain.database.annotations.Id;
import domain.time.Time;

/**
 * Appointment represents an appointment document stored within the database.
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

    @Column(name = "location")
    private String location;

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

    /**
     * Default constructor to initialize an empty Appointment with the provided id
     *
     * @param appointment_id the appointment id
     */
    public Appointment(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    /**
     * @return the appointment id
     */
    public int getAppointmentId() {
        return appointment_id;
    }

    /**
     * @return the appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the appointment type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the start time
     */
    public Time getStart() {
        return start;
    }

    /**
     * @param start the start time to set
     */
    public void setStart(Time start) {
        this.start = start;
    }

    /**
     * @return the end time
     */
    public Time getEnd() {
        return end;
    }

    /**
     * @param end the end time to set
     */
    public void setEnd(Time end) {
        this.end = end;
    }

    /**
     * @return the user who created the appointment
     */
    public String getCreatedBy() {
        return created_by;
    }

    /**
     * @param created_by the user to that created the appointment
     */
    public void setCreatedBy(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @return the user that updated the appointment
     */
    public String getLastUpdatedBy() {
        return last_updated_by;
    }

    /**
     * @param last_updated_by the that updated the appointment
     */
    public void setLastUpdatedBy(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * @return the create date
     */
    public Time getCreateDate() {
        return create_date;
    }

    /**
     * @param create_date the create date to set
     */
    public void setCreateDate(Time create_date) {
        this.create_date = create_date;
    }

    /**
     * @return the last update time
     */
    public Time getLastUpdate() {
        return last_update;
    }

    /**
     * @param last_update the last update time to set
     */
    public void setLastUpdate(Time last_update) {
        this.last_update = last_update;
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
     * @return the user_id
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the customer id
     */
    public int getCustomerId() {
        return customer_id;
    }

    /**
     * @param customer_id the customer id to set
     */
    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
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
