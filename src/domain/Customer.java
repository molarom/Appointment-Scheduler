package domain;

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
    private String create_date;
    private String created_by;
    private String last_update;
    private String last_update_by;
    private int division_id;

    // TODO: add create and update fields to default constructor.

    /**
     * Default constructor to initialize an empty Customer.
     */
    public Customer() {}

    /**
     * Constructor to initialize a new user.
     *
     * @param customer_name the customer's name
     * @param postal_code the customer's postal_code
     * @param phone the customer's phone number
     * @param create_date the date the customer was created
     * @param created_by the user that created the customer
     * @param last_update the date the customer was updated
     */
    public Customer(String customer_name, String postal_code, String phone, String create_date, String created_by, String last_update) {
       this.customer_name = customer_name;
       this.postal_code = postal_code;
       this.phone = phone;
       this.create_date = create_date;
       this.created_by = created_by;
       this.last_update = last_update;
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
       this.customer_name = customer_name;
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
                ", create_date='" + create_date + '\'' +
                ", created_by='" + created_by + '\'' +
                ", last_update='" + last_update + '\'' +
                ", last_update_by='" + last_update_by + '\'' +
                ", division_id=" + division_id +
                '}';
    }
}
