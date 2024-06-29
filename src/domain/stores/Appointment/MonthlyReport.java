package domain.stores.Appointment;

import domain.database.annotations.Column;

/**
 * MonthlyReport represent the total number of appointment types by month
 *
 * @author Brandon Epperson
 */
public class MonthlyReport {
    @Column(name = "month")
    private String month;

    @Column(name = "type")
    private String type;

    @Column(name = "total")
    private int total;

    /**
     * Constructor to initialize an empty MonthlyReport
     */
    public MonthlyReport() {
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @return the appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "MonthlyReport{" +
                "month='" + month + '\'' +
                ", type='" + type + '\'' +
                ", total=" + total +
                '}';
    }
}
