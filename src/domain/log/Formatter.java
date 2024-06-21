package domain.log;

import domain.time.Time;

import java.util.logging.LogRecord;

/**
 * Formatter extends the util.logging.Formatter
 * for customized log output.
 *
 * @author Brandon Epperson
 */
public class Formatter extends java.util.logging.Formatter {
    @Override
    public String format(LogRecord record) {
        Time t = new Time();
        t.fromMillis(record.getMillis());
        return record.getLevel() + "|"
                + record.getSourceClassName() + "."
                + record.getSourceMethodName() + "|"
                + t.toUTCString() + "|"
                + record.getMessage() + "\n";
    }
}
