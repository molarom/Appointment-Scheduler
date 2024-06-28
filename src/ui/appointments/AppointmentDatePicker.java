package ui.appointments;

import domain.time.Time;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Skin;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * AppointmentDatePicker is the date picker displayed on
 * the Appointment forms.
 *
 * @author Brandon Epperson
 */
public class AppointmentDatePicker extends DatePicker {
    private ObjectProperty<LocalTime> timeValue = new SimpleObjectProperty<>();
    private ObjectProperty<Time> dateTimeValue;

    /**
     * Constructs a new AppointmentDatePicker
     */
    public AppointmentDatePicker() {
        setValue(LocalDate.now());
        //setDateTimeValue();
        setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return dateTimeValue.get().withZone(Time.SystemT).toString();
            }

            @Override
            public LocalDate fromString(String string) {
                return dateTimeValue.get().fromString(string, Time.SystemT).toLocalDate();
            }
        });
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return null;
    }

}
