package ui.divisions;

import app.controllers.DivisionsController;
import domain.Country;
import domain.FirstLevelDivision;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import ui.countries.CountryComboBox;


/**
 * DivisionComboBox is a comboBox containing a list of Countries
 *
 * @author Brandon Epperson
 */
public class DivisionComboBox extends ComboBox<FirstLevelDivision> {
    /**
     * DivisionComboBox constructs a new CountryComboBox
     */
    public DivisionComboBox(CountryComboBox countryComboBox) {
        this.itemsProperty().bind(new DivisionObjectBinding(countryComboBox));

        this.setCellFactory(view -> new DivisionListCell());
        this.setButtonCell(new DivisionListCell());
    }

    /**
     * Sets the FirstLevelDivision in the selection model.
     *
     * @param division the FirstLevelDivision object to search for.
     */
    public void setDivision(FirstLevelDivision division) {
        if (division != null) {
            this.getItems().forEach(val -> {
                if (val.getId() == division.getId()) {
                    this.getSelectionModel().select(val);
                }
            });
            return;
        }
        this.getSelectionModel().clearSelection();
    }

    /**
     * DivisionListCell ensures that only the division name is displayed
     * in the UI.
     */
    private static class DivisionListCell extends ListCell<FirstLevelDivision> {
        @Override
        public void updateItem(FirstLevelDivision item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getDivisionName());
            } else {
                setText(null);
            }
        }
    }

    /**
     * DivisionObjectBinding binds the values of the DivisionComboBox to a CountryComboBox.
     */
    private static class DivisionObjectBinding extends ObjectBinding<ObservableList<FirstLevelDivision>> {
        private static CountryComboBox countryComboBox;

        DivisionObjectBinding(CountryComboBox countryComboBox) {
            DivisionObjectBinding.countryComboBox = countryComboBox;
            super.bind(countryComboBox.valueProperty());
        }

        @Override
        protected ObservableList<FirstLevelDivision> computeValue() {
            Country country = countryComboBox.getValue();
            if (country != null) {
                return DivisionsController.getAllByCountry(country);
            }
            return FXCollections.emptyObservableList();
        }
    }
}
