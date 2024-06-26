package domain;

import domain.database.annotations.Column;
import domain.time.Time;

/**
 * CustomerView represents a read-only view of the data returned from the
 * relationships between Customers, FirstLevelDivisions, and Countries.
 *
 * @author Brandon Epperson
 */
public class CustomerView {
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

    @Column(name = "division_id")
    private int division_id;

    @Column(name = "division")
    private String division_name;

    @Column(name = "country_id")
    private int country_id;

    @Column(name = "country")
    private String country;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "last_updated_by")
    private String last_updated_by;

    @Column(name = "create_date")
    private Time create_date;

    @Column(name = "last_update")
    private Time last_update;

    /**
     * CustomerView constructs an empty CustomerView
     */
    public CustomerView() {
    }

    ;

    /**
     * @return the customer's id
     */
    public int getCustomerId() {
        return customer_id;
    }

    /**
     * @return the customer_name
     */
    public String getCustomerName() {
        return customer_name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the postal_code
     */
    public String getPostalCode() {
        return postal_code;
    }

    /**
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the division id
     */
    public int getDivisionId() {
        return division_id;
    }

    /**
     * @return the division name
     */
    public String getDivisionName() {
        return division_name;
    }

    /**
     * @return the country id
     */
    public int getCountryId() {
        return country_id;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the create_date
     */
    public Time getCreateDate() {
        return create_date;
    }

    /**
     * @return the user that created the customer
     */
    public String getCreatedBy() {
        return created_by;
    }

    /**
     * @return the last_update
     */
    public Time getLastUpdate() {
        return last_update;
    }

    /**
     * @return the last_updated_by
     */
    public String getLastUpdatedBy() {
        return last_updated_by;
    }

    @Override
    public String toString() {
        return "CustomerView{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", address='" + address + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", phone='" + phone + '\'' +
                ", division_id=" + division_id +
                ", division_name='" + division_name + '\'' +
                ", country_id=" + country_id +
                ", country='" + country + '\'' +
                ", created_by='" + created_by + '\'' +
                ", last_updated_by='" + last_updated_by + '\'' +
                ", create_date=" + create_date.toString() +
                ", last_update=" + last_update.toString() +
                '}';
    }
}
