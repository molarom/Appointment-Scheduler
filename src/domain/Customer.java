package domain;

import domain.time.Time;

import java.time.LocalDate;

/**
 * Customer represents a customer object for use in the application.
 *
 * @author Brandon Epperson
 */
public class Customer {
    private int customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private String created_by;
    private String last_update_by;
    private Time create_date;
    private Time last_update;

    // TODO: Add functionality for setting the division_id
    private int division_id;


    /**
     * Default constructor to initialize an empty Customer.
     */
    public Customer() {}

    /**
     * Constructor to create a new Customer.
     *
     * @param customer_name the customer's name
     * @param postal_code the customer's postal_code
     * @param phone the customer's phone number
     * @param create_date the date the customer was created
     * @param created_by the user that created the customer
     * @param last_update the date the customer was updated
     * @param last_update_by the user that updated the customer
     */
    public Customer(String customer_name, String postal_code, String phone, Time create_date, String created_by, Time last_update, String last_update_by) {
       this.customer_name = customer_name;
       this.postal_code = postal_code;
       this.phone = phone;
       this.created_by = created_by;
       this.last_update_by = last_update_by;
       this.setCreateDate(create_date);
       this.setLastUpdate(last_update);
    }

    /**
     * Constructor to create a new Customer from database document.
     *
     * @param customer_id the customer's id
     */
    public Customer(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the customer_name
     */
    public String getName() {
        return customer_name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
       this.customer_name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postal_code
     */
    public String getPostalCode() {
        return postal_code;
    }

    /**
     * @param postal_code the postal_code to set
     */
    public void setPostalCode(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the create_date
     */
    public Time getCreateDate() {
        return create_date;
    }

    /**
     * @param create_date to set
     */
    public void setCreateDate(Time create_date) {
        this.create_date = create_date;
    }

    /**
     * @return the user that created the customer
     */
    public String getCreatedBy() {
        return created_by;
    }

    /**
     * @param created_by set the user that created this customer
     */
    public void setCreatedBy(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @return the last_update
     */
    public Time getLastUpdate() {
        return last_update;
    }

    /**
     * @param last_update the last_update time to set
     */
    public void setLastUpdate(Time last_update) {
        this.last_update = last_update;
    }

    /**
     * @return the last_updated_by
     */
    public String getLastUpdatedBy() {
        return last_update_by;
    }

    /**
     * @param last_update_by the user that updated this customer
     */
    public void setLastUpdatedBy(String last_update_by) {
        this.last_update_by = last_update_by;
    }

    /**
     * @return a customer object represented as a string
     */
    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", address='" + address + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", phone='" + phone + '\'' +
                ", create_date='" + create_date.UTCtoString() + '\'' +
                ", created_by='" + created_by + '\'' +
                ", last_update='" + last_update.LocaltoString() + '\'' +
                ", last_update_by='" + last_update_by + '\'' +
                ", division_id=" + division_id +
                '}';
    }
}
