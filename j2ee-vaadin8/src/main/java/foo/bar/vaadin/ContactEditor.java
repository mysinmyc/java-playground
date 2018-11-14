package foo.bar.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import foo.bar.backend.Contact;

public class ContactEditor extends FormLayout {

	public enum ContactAction {
		NONE, SAVE, DELETE
	}

	public interface ContactActionListener {
		public void onEvent(ContactAction action, Contact contact);
	}

	TextField name;
	TextField address;
	TextField number;

	public ContactEditor() {
		buildAndBuind();
	}

	Button saveButton;
	Button deleteButton;

	public void buildAndBuind() {

		setWidth("500px");
		setMargin(true);
		name = new TextField("Name");
		name.focus();
		address = new TextField("Address");
		number = new TextField("Number");
		addComponents(name, address, number);
		binder.bindInstanceFields(this);

		saveButton = new Button("save");
		saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		saveButton.addClickListener(e -> {

			try {
				binder.writeBean(contact);
			} catch (ValidationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (contactActionListener != null) {
				contactActionListener.onEvent(ContactAction.SAVE, contact);
			}
		});
		


		deleteButton = new Button("delete");
		deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
		deleteButton.addClickListener(e -> {
			if (contactActionListener != null) {
				contactActionListener.onEvent(ContactAction.DELETE, contact);
			}
		});
		addComponent(new HorizontalLayout(saveButton,deleteButton));
	}

	ContactActionListener contactActionListener;

	Binder<Contact> binder = new Binder<>(Contact.class);

	Contact contact;

	public ContactActionListener getContactActionListener() {
		return contactActionListener;
	}

	public void setContactActionListener(ContactActionListener contactActionListener) {
		this.contactActionListener = contactActionListener;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
		binder.readBean(contact);
	}

}
