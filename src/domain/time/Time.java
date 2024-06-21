package domain.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Time serves to provide a standard object to interact with different
 * time types.
 * Internally everything is stored as UTC.
 *
 * @author Brandon Epperson
 */
public class Time {
    private ZonedDateTime UTCtime;
    private final ZoneId UTC = ZoneId.of("UTC");
    private final ZoneId EST = ZoneId.of("America/New_York");
    private final ZoneId System = ZoneId.systemDefault();


    /**
     * Default constructor to initialize a new Time.
     */
    public Time() {
        this.UTCtime = LocalDateTime.now().atZone(UTC);
    }

    /**
     * @return UTC datetime
     */
    public ZonedDateTime getUTC() {
        return UTCtime;
    }

    /**
     * @return Local datetime
     */
    public ZonedDateTime getLocal() {
        return UTCtime.toLocalDateTime().atZone(System);
    }

    /**
     * @return Eastern Standard datetime
     */
    public ZonedDateTime getEST() {
        return UTCtime.toLocalDateTime().atZone(EST);
    }

    /**
     * fromObject attempt to convert a generic object to a Time
     * returns null on error.
     *
     * @param o the object to convert
     */
    public static Time fromObject(Object o) {
        Time t = new Time();
        if (o == null) {
            java.lang.System.err.println("provided time object is null");
            return null;
        }
        if (o instanceof java.sql.Time sqlTime) {
            t.fromSqlTime(sqlTime);
        } else if (o instanceof java.sql.Timestamp sqlTimestamp) {
            t.fromSqlTimestamp(sqlTimestamp);
        } else if (o instanceof java.sql.Date sqlDate) {
            t.fromSqlDate(sqlDate);
        } else if (o instanceof LocalDateTime localDateTime) {
            t.fromLocalDateTime(localDateTime);
        } else {
            java.lang.System.err.println("Unsupported time type: " + o.getClass());
            return null;
        }
        return t;
    }

    /**
     * fromSqlTime converts a sql.Time to Time
     *
     * @param sqlTime the time to convert
     */
    public void fromSqlTime(java.sql.Time sqlTime) {
        LocalDateTime dt = LocalDateTime.from(sqlTime.toLocalTime());
        this.UTCtime = dt.atZone(UTC);
    }

    /**
     * fromSqlTimestamp converts a sql.Timestamp to Time
     *
     * @param sqlTimestamp the timestamp to convert
     */
    public void fromSqlTimestamp(java.sql.Timestamp sqlTimestamp) {
        LocalDateTime dt = sqlTimestamp.toLocalDateTime();
        this.UTCtime = dt.atZone(UTC);
    }

    /**
     * @return a new sql.Timestamp
     */
    public java.sql.Timestamp toSqlTimestamp() {
        return java.sql.Timestamp.valueOf(UTCtime.toLocalDateTime());
    }

    /**
     * fromSqlDate converts a sql.Date to Time
     *
     * @param sqlDate the date to convert
     */
    public void fromSqlDate(java.sql.Date sqlDate) {
        this.UTCtime = sqlDate.toInstant().atZone(UTC);
    }

    /**
     * fromLocalDateTime converts a LocalDateTime to Time
     *
     * @param localDateTime the LocalDateTime to convert
     */
    public void fromLocalDateTime(LocalDateTime localDateTime) {
        this.UTCtime = localDateTime.atZone(UTC);
    }

    /**
     * @return a new java.time.LocalDateTime
     */
    public java.time.LocalDateTime toLocalDateTime() {
        return UTCtime.toLocalDateTime();
    }

    public void fromMillis(long millis) {
        this.UTCtime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), UTC);
    }

    /**
     * @return the UTC time as a string
     */
    public String toUTCString() {
        return UTCtime.toString();
    }

    /**
     * @return the EST time as a string
     */
    public String toESTString() {
        return UTCtime.toLocalDateTime().atZone(EST).toString();
    }

    /**
     * @return the system local time as a string
     */
    public String toLocalString() {
        return UTCtime.toLocalDateTime().atZone(System).toString();
    }


    /**
     * getDelta returns the difference between two times as a string.
     *
     * @param since the Time to compare
     * @return string representation of difference.
     */
    // TODO: Update this or make new method to get minutes.
    public String getDelta(Time since) {
        LocalDateTime sinceDt = since.toLocalDateTime();
        long minutes = sinceDt.until(UTCtime.toLocalDateTime(), ChronoUnit.MINUTES);
        long seconds = sinceDt.until(UTCtime.toLocalDateTime(), ChronoUnit.SECONDS);
        long milliseconds = sinceDt.until(UTCtime.toLocalDateTime(), ChronoUnit.MILLIS);

        return minutes + ":" + seconds + ":" + milliseconds;
    }

}
