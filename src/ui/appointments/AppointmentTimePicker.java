package ui.appointments;

import domain.time.Time;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
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
    public AppointmentTimePicker(AppointmentDatePicker appointmentDatePicker) {
        this.itemsProperty().bind(new AppointmentTimeBinding(appointmentDatePicker));

        this.setCellFactory(view -> new AppointmentListCell());
        this.setButtonCell(new AppointmentListCell());
    }

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

    private static class AppointmentTimeBinding extends ObjectBinding<ObservableList<Time>> {
        private static final Time businessStart = Time.fromLocalTime(LocalTime.of(8, 0), Time.EST);
        private static final Time businessEnd = Time.fromLocalTime(LocalTime.of(22, 0), Time.EST);

        private static AppointmentDatePicker appointmentDatePicker;
        AppointmentTimeBinding(AppointmentDatePicker appointmentDatePicker) {
           AppointmentTimeBinding.appointmentDatePicker = appointmentDatePicker;
            super.bind(appointmentDatePicker.valueProperty());
        }

        @Override
        protected ObservableList<Time> computeValue() {
            LocalDate date = appointmentDatePicker.getValue();
            List<Time> timeList = new ArrayList<>();
            if (date != null) {
                for (int i = 0; i < 24; i++) {
                    for  (int j = 0; j < 4; j++) {
                        Time t = Time.fromLocalTime(LocalTime.of(i, j*15), date, Time.SystemT);

                        if (t.isInRange(businessStart, businessEnd)) {
                            timeList.add(t);
                        }
                    }
                }
                return FXCollections.observableList(timeList);
            }
            return FXCollections.emptyObservableList();
        }
    }
}