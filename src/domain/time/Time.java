package domain.time;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Time serves to provide a standard object to interact with different
 * time types.
 *
 * @author Brandon Epperson
 */
public class Time {
    private ZonedDateTime zDT;
    public static final ZoneId UTC = ZoneId.of("UTC");
    public static final ZoneId EST = ZoneId.of("America/New_York");
    public static final ZoneId SystemT = ZoneId.systemDefault();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm");


    /**
     * Default constructor to initialize a new Time.
     */
    public Time() {
        this.zDT = ZonedDateTime.now();
    }

    /**
     * Creates a new Time from an existing ZonedDateTime
     */
    public Time(ZonedDateTime zTime) {
        this.zDT = zTime;
    }

    /**
     * WithZone adds zone context to the time object.
     * @param zone the zone to set.
     * @return the updated Time
     */
    public Time withZone(ZoneId zone) {
        this.zDT = this.zDT.withZoneSameInstant(zone);
        return this;
    }

    /**
     * @return The ZonedDateTime object.
     */
    public ZonedDateTime getTime() {
        return this.zDT;
    }

    /**
     * fromObject attempt to convert a generic object to a Time
     * returns null on error.
     *
     * @param o the object to convert
     * @param zone the zone the time originated from.
     * @return the Time
     */
    public static Time fromObject(Object o, ZoneId zone) {
        if (o == null) {
            java.lang.System.err.println("provided time object is null");
            return null;
        }
        if (o instanceof java.sql.Time sqlTime) {
            return Time.fromSqlTime(sqlTime, zone);
        } else if (o instanceof Timestamp sqlTimestamp) {
            return Time.fromSqlTimestamp(sqlTimestamp, zone);
        } else if (o instanceof Date sqlDate) {
            return Time.fromSqlDate(sqlDate, zone);
        } else if (o instanceof LocalDateTime localDateTime) {
            return fromLocalDateTime(localDateTime, zone);
        } else if (o instanceof LocalDate localDate) {
            return fromLocalDate(localDate, zone);
        } else if (o instanceof LocalTime localTime) {
            return fromLocalTime(localTime, zone);
        } else {
            java.lang.System.err.println("Unsupported time type: " + o.getClass());
            return null;
        }
    }

    /**
     * fromSqlTime converts a sql.Time to Time
     *
     * @param sqlTime the time to convert
     * @param zone the zone the time originated from.
     * @return the Time
     */
    public static Time fromSqlTime(java.sql.Time sqlTime, ZoneId zone) {
        return new Time(LocalDateTime.from(sqlTime.toLocalTime()).atZone(zone));
    }

    /**
     * fromSqlTimestamp converts a sql.Timestamp to Time
     *
     * @param sqlTimestamp the timestamp to convert
     * @param zone the zone the time originated from.
     * @return the Time
     */
    public static Time fromSqlTimestamp(Timestamp sqlTimestamp, ZoneId zone) {
         return new Time(sqlTimestamp.toLocalDateTime().atZone(zone));
    }

    /**
     * @return a new sql.Timestamp
     */
    public Timestamp toSqlTimestamp() {
        return Timestamp.valueOf(zDT.toLocalDateTime());
    }

    /**
     * fromSqlDate converts a sql.Date to Time
     *
     * @param sqlDate the date to convert
     * @param zone the zone the time originated from.
     * @return the Time
     */
    public static Time fromSqlDate(Date sqlDate, ZoneId zone) {
        return new Time(sqlDate.toInstant().atZone(UTC));
    }

    /**
     * fromLocalDateTime converts a LocalDateTime to Time
     *
     * @param localDateTime the LocalDateTime to convert
     * @param zone the zone the time originated from.
     * @return the Time
     */
    public static Time fromLocalDateTime(LocalDateTime localDateTime, ZoneId zone) {
        return new Time (localDateTime.atZone(zone));
    }

    /**
     * fromLocalDate converts a LocalDate to Time
     *
     * @param localDate the LocalDate to convert
     * @return the Time
     */
    public static Time fromLocalDate(LocalDate localDate, ZoneId zone) {
        return new Time(localDate.atStartOfDay().atZone(zone));
    }

    /**
     * fromLocalTime converts a LocalTime to Time
     *
     * @param localTime the LocalTime to convert
     * @param zone the zone the time originated from.
     * @return the Time
     */
    public static Time fromLocalTime(LocalTime localTime, ZoneId zone) {
        return fromLocalTime(localTime, null, zone);
    }

    /**
     * fromLocalTime converts a LocalTime to Time
     * with a LocalDate provided.
     *
     * @param localTime the LocalTime to convert
     * @param localDate the LocalDate to add
     * @param zone the zone the time originated from.
     * @return the Time
     */
    public static Time fromLocalTime(LocalTime localTime, LocalDate localDate, ZoneId zone) {
        if (localDate != null) {
            return new Time(localTime.atDate(localDate).atZone(zone));
        }

        return new Time(localTime.atDate(LocalDate.now()).atZone(zone));
    }

    /**
     * @return a new java.time.LocalDateTime
     */
    public LocalDateTime toLocalDateTime() {
        return zDT.toLocalDateTime();
    }

    /**
     * Populates a new Time from milliseconds
     * @param millis the milliseconds
     * @param zone the zone the time originated from.
     */
    public static Time fromMillis(long millis, ZoneId zone) {
        return new Time(ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), zone));
    }

    /**
     * from string reads a date and time string to populate a Time object.
     * @param date string in the format 'yyyy-MM-dd'
     * @param time string in the format 'HH:mm'
     */
    public Time fromString(String date, String time, ZoneId zone) {
       return new Time(LocalDateTime.parse(date + " - " + time, formatter).atZone(zone));
    }

    /**
     * Adds the provided number of months to an existing Time.
     * @param months the months to add
     */
    public Time addMonths(int months) {
        this.zDT = zDT.plusMonths(months);
        return this;
    }

    /**
     * getDeltaMonths returns the difference between two Times in months.
     * @param since the Time to compare
     * @return the number of months as an int
     */
    public int getDeltaMonths(Time since) {
        long deltaMonths = ChronoUnit.MONTHS.between(since.getTime(), zDT);
        return Long.valueOf(deltaMonths).intValue();
    }

    /**
     * Adds the provided number of weeks to an existing Time.
     * @param weeks the number of weeks to add
     */
    public Time addWeeks(int weeks) {
        this.zDT = zDT.plusWeeks(weeks);
        return this;
    }

    /**
     * getDeltaWeeks returns the difference between two Times in weeks.
     * @param since the Time to compare
     * @return the number of weeks as an int
     */
    public int getDeltaWeeks(Time since) {
        long deltaWeeks = ChronoUnit.WEEKS.between(since.getTime(), zDT);
        return Long.valueOf(deltaWeeks).intValue();
    }

    /**
     * Checks is a Time lands on a weekday in EST.
     * @return true if it is a weekday
     */
    public boolean isWeekday() {
       switch(this.getTime().getDayOfWeek()) {
           case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
               return true;
           }
           default -> {
               return false;
           }
       }
    }

    /**
     * Checks if a Time is within the given range of Times.
     * All times are converted internally to UTC for comparison.
     *
     * @param startT the start time
     * @param endT the end time
     * @return true if in range
     */
    public boolean isInRange(Time startT, Time endT) {

        Instant thisI = this.withZone(UTC).getTime().toInstant();
        Instant startI = startT.withZone(UTC).getTime().toInstant();
        Instant endI = endT.withZone(UTC).getTime().toInstant();

        boolean b = !thisI.isBefore(startI)
                && thisI.isBefore(endI);
        System.out.println("thisI (UTC): " + thisI +
                " startI (UTC): " + startI +
                " endI (UTC): " + endI +
                " result: " + b
        );

        return b;
    }

    /**
     * @return the Time as a string
     */
    @Override
    public String toString() {
        return formatter.format(zDT);
    }
}
