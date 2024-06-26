package ui.appointments;

import domain.time.Time;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

/**
 * AppointmentDatePicker is the date picker displayed on
 * the Appointment forms.
 *
 * @author Brandon Epperson
 */
public class AppointmentDatePicker extends DatePicker {
    /**
     * Constructs a new AppointmentDatePicker
     */
    public AppointmentDatePicker() {
        this.setDayCellFactory(date -> new AppointmentDateCell());
        this.setValue(LocalDate.now());
    }

    /**
     * AppointmentDateCell ensures that invalid dates cannot be selected.
     */
    private static class AppointmentDateCell extends DateCell {
        @Override
        public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);

            LocalDate now = LocalDate.from(new Time().withZone(Time.SystemT).getTime());
            Time t = Time.fromObject(item, Time.SystemT).withZone(Time.EST);

            if (
                    !t.isWeekday() || item.isBefore(now)
            ) {
                setDisable(true);
            }
        }
    }
}
