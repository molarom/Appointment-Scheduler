package ui.contacts;

import app.controllers.ContactController;
import domain.stores.Contact.Contact;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

/**
 * ContactComboBox is a comboBox containing a list of Countries
 *
 * @author Brandon Epperson
 */
public class ContactComboBox extends ComboBox<Contact> {
    /**
     * ContactComboBox constructs a new ContactComboBox
     */
    public ContactComboBox() {
        this.getItems().addAll(
                ContactController.getAllContacts()
        );

        this.setCellFactory(view -> new ContactListCell());
        this.setButtonCell(new ContactListCell());
    }

    /**
     * Set the contact in the selection model.
     *
     * @param contact the contact to search for.
     */
    public void setContact(Contact contact) {
        if (contact != null) {
            this.getItems().forEach(val -> {
                if (val.getContactId() == contact.getContactId()) {
                    this.getSelectionModel().select(val);
                }
            });
            return;
        }
        this.getSelectionModel().clearSelection();
    }

    /**
     * ContactListCell ensures that only the contact name is displayed
     * in the UI.
     */
    private static class ContactListCell extends ListCell<Contact> {
        @Override
        public void updateItem(Contact item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getContactName());
            } else {
                setText(null);
            }
        }
    }
}
