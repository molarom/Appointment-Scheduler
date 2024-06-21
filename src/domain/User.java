package domain;

import domain.database.annotations.Column;
import domain.database.annotations.Id;
import domain.time.Time;

public class User {
    @Id
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "password")
    private String password;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "last_updated_by")
    private String last_updated_by;

    @Column(name = "create_date")
    private Time create_date;

    @Column(name = "last_update")
    private Time last_update;

    /**
     * Default constructor to initialize an empty user.
     */
    public User() {
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
    public String getLastUpdateBy() {
        return last_updated_by;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", created_by='" + created_by + '\'' +
                ", last_updated_by='" + last_updated_by + '\'' +
                ", create_date=" + create_date.toLocalString() +
                ", last_update=" + last_update.toLocalString() +
                '}';
    }
}
