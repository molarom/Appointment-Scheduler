package domain;

import domain.time.Time;

/**
 * FirstLevelDivisions represent the first level of an address.
 * Example: Canada -> Quebec is the first level division.
 *
 * @author Brandon Epperson
 */
public class FirstLevelDivisions {
    private int division_id;
    private String division_name;
    private String created_by;
    private String last_update_by;
    private Time create_date;
    private Time last_update;

    // TODO: Add functionality for setting country_id


    public FirstLevelDivisions() {}
}
