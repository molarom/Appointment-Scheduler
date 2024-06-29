package domain.stores.Customer;

import domain.database.annotations.Column;

/**
 * CustomerReport represents the breakdown of customers per country.
 *
 * @author Brandon Epperson
 */
public class CustomerReport {
    @Column(name = "country")
    private String country;

    @Column(name = "count")
    private int count;

    /**
     * Constructs a new empty CustomerReport
     */
    public CustomerReport() {
    }

    /**
     * @return the country name
     */
    public String getCountryName() {
        return country;
    }

    /**
     * @return the count of customers
     */
    public int getCount() {
        return count;
    }
}
