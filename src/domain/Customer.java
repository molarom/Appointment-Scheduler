package domain;

import domain.database.annotations.Column;
import domain.database.annotations.Id;
import domain.time.Time;

/**
 * Customer represents a customer object for use in the application.
 *
 * @author Brandon Epperson
 */
public class Customer {
    @Id
    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "last_updated_by")
    private String last_updated_by;

    @Column(name = "create_date")
    private Time create_date;

    @Column(name = "last_update")
    private Time last_update;

    @Column(name = "division_id")
    private int division_id;


    /**
     * Default constructor to initialize an empty Customer.
     */
    public Customer() {
    }

    /**
     * Constructor to create a new Customer.
     *
     * @param customer_name   the customer's name
     * @param address         the customer's address
     * @param postal_code     the customer's postal_code
     * @param phone           the customer's phone number
     * @param created_by      the user that created the customer
     * @param last_updated_by the user that updated the customer
     */
    public Customer(String customer_name, String address, String postal_code, String phone, String created_by, String last_updated_by) {
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.created_by = created_by;
        this.last_updated_by = last_updated_by;
    }

    /**
     * @return the customer's id
     */
    public int getCustomerId() {
        return customer_id;
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
        return last_updated_by;
    }

    /**
     * @param last_update_by the user that updated this customer
     */
    public void setLastUpdateBy(String last_update_by) {
        this.last_updated_by = last_update_by;
    }

    /**
     * @return the division id
     */
    public int getDivisionId() {
        return division_id;
    }

    /**
     * @param division_id the division id to set.
     */
    public void setDivisionId(int division_id) {
        this.division_id = division_id;
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
                ", create_date='" + create_date.toUTCString() + '\'' +
                ", created_by='" + created_by + '\'' +
                ", last_update='" + last_update.toLocalString() + '\'' +
                ", last_updated_by='" + last_updated_by + '\'' +
                ", division_id=" + division_id +
                '}';
    }
}
