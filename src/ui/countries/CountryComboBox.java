package ui.countries;

import app.controllers.CountryController;
import domain.Country;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

/**
 * CountryComboBox is a comboBox containing a list of Countries
 *
 * @author Brandon Epperson
 */
public class CountryComboBox extends ComboBox<Country> {
    /**
     * CountryComboBox constructs a new CountryComboBox
     */
    public CountryComboBox() {
        this.getItems().addAll(
                CountryController.GetAllCountries()
        );

        this.setCellFactory(view -> new CountryListCell());
        this.setButtonCell(new CountryListCell());
    }

    /**
     * Set the country in the selection model.
     *
     * @param country the country object to search for.
     */
    public void setCountry(Country country) {
        if (country != null) {
            this.getItems().forEach(val -> {
                if (val.getCountryId() == country.getCountryId()) {
                    this.getSelectionModel().select(val);
                }
            });
            return;
        }
        this.getSelectionModel().clearSelection();
    }

    /**
     * CountryListCell ensures that only the country name is displayed
     * in the UI.
     */
    private static class CountryListCell extends ListCell<Country> {
        @Override
        public void updateItem(Country item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getCountryName());
            } else {
                setText(null);
            }
        }
    }
}
