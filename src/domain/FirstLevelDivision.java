package domain;

import domain.database.annotations.Column;
import domain.database.annotations.Id;
import domain.time.Time;

/**
 * FirstLevelDivision represent the first level of an address.
 * Example: Canada -> Quebec is the first level division.
 *
 * @author Brandon Epperson
 */
public class FirstLevelDivision {
    @Id
    @Column(name = "division_id")
    private int division_id;

    @Column(name = "division")
    private String division_name;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "last_update_by")
    private String last_update_by;

    @Column(name = "create_date")
    private Time create_date;

    @Column(name = "last_update")
    private Time last_update;

    // TODO: Add functionality for getting country_id
    private int country_id;

    /**
     * Default constructor to initialize an empty FirstLevelDivision
     */
    public FirstLevelDivision() {}

    /**
     * Constructor to create a new FirstLevelDivision from a database document.
     *
     * @param division_id the division's id
     */
    public FirstLevelDivision(int division_id) {
        this.division_id = division_id;
    }

    /**
     * @return the division id
     */
    public int getId() {
        return division_id;
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
     * @return the user that created the entry
     */
    public String getCreatedBy() {
        return created_by;
    }

    /**
     * @param created_by set the user that created this entry
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
     * @param last_update_by the user that updated this entry
     */
    public void setLastUpdatedBy(String last_update_by) {
        this.last_update_by = last_update_by;
    }

    @Override
    public String toString() {
        return "FirstLevelDivision{" +
                "division_id=" + division_id +
                ", division_name='" + division_name + '\'' +
                ", created_by='" + created_by + '\'' +
                ", last_update_by='" + last_update_by + '\'' +
                ", create_date=" + create_date +
                ", last_update=" + last_update +
                ", country_id=" + country_id +
                '}';
    }
}
