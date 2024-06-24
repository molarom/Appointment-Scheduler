package domain;

import domain.database.annotations.Column;
import domain.database.annotations.Id;
import domain.time.Time;

/**
 * Country represents a country object for use in the application.
 *
 * @author Brandon Epperson
 */
public class Country {
    @Id
    @Column(name = "country_id")
    private int country_id;

    @Column(name = "country")
    private String country_name;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "last_updated_by")
    private String last_updated_by;

    @Column(name = "create_date")
    private Time create_date;

    @Column(name = "last_update")
    private Time last_update;

    /**
     * Default constructor to initialize an empty Country.
     */
    public Country() {
        Time t = new Time();
        create_date = t;
        last_update = t;
    }

    /**
     * Constructs a new Country object from provided parameters.
     *
     * @param country_id   the country id
     * @param country_name the country name
     */
    public Country(int country_id, String country_name) {
        this.country_id = country_id;
        this.country_name = country_name;
    }

    /**
     * @return the country id.
     */
    public int getCountryId() {
        return country_id;
    }

    /**
     * @return the country name
     */
    public String getCountryName() {
        return country_name;
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
    public String getLastUpdateBy() {
        return last_updated_by;
    }

    /**
     * @param last_update_by the user that updated this customer
     */
    public void setLastUpdateBy(String last_update_by) {
        this.last_updated_by = last_update_by;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_id=" + country_id +
                ", country_name='" + country_name + '\'' +
                ", created_by='" + created_by + '\'' +
                ", last_updated_by='" + last_updated_by + '\'' +
                ", create_date=" + create_date +
                ", last_update=" + last_update +
                '}';
    }
}
