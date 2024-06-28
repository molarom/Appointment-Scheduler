package ui.appointments;

import domain.time.Time;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * AppointmentTimePicker is the Appointment time slot selector
 * displayed in the Appointment forms.
 */
public class AppointmentTimePicker extends ComboBox<Time> {
    private static final Time businessStart = Time.fromLocalTime(LocalTime.of(8, 0), Time.EST);
    private static final Time businessEnd = Time.fromLocalTime(LocalTime.of(22, 0), Time.EST);

    /**
     * Creates a new AppointmentTimePicker
     *
     * @param datePicker the datepicker to bind to.
     */
    public AppointmentTimePicker(DatePicker datePicker) {
        this.itemsProperty().bind(new AppointmentDateBinding(datePicker));

        this.setCellFactory(view -> new AppointmentListCell());
        this.setButtonCell(new AppointmentListCell());
    }

    /**
     * AppointmentListCell updates the cells with the time value as a string.
     */
    private static class AppointmentListCell extends ListCell<Time> {
        @Override
        public void updateItem(Time item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.withZone(Time.SystemT).toString());
            } else {
                setText(null);
            }
        }
    }

    /**
     * AppointmentDateBinding binds the list of Time to the LocalDate chosen on the
     * AppointmentDatePicker.
     */
    private static class AppointmentDateBinding extends ObjectBinding<ObservableList<Time>> {
        private static DatePicker appointmentDatePicker;

        AppointmentDateBinding(DatePicker datePicker) {
            AppointmentDateBinding.appointmentDatePicker = appointmentDatePicker;
            super.bind(appointmentDatePicker.valueProperty());
        }

        @Override
        protected ObservableList<Time> computeValue() {
            LocalDate date = appointmentDatePicker.getValue();
            if (date != null) {
                System.out.println(appointmentDatePicker.getValue());
                return AppointmentTimePicker.generateTimeList(date);
            }
            return FXCollections.emptyObservableList();
        }
    }

    private static ObservableList<Time> generateTimeList(LocalDate date) {
        List<Time> items = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 4; j++) {
                Time t = Time.fromLocalTime(LocalTime.of(i, j * 15), date, Time.SystemT);

                // TODO: Add check for existing appointments.
                if (t.isInRange(businessStart, businessEnd)) {
                    items.add(t);
                }
            }
        }
        return FXCollections.observableList(items);
    }
}