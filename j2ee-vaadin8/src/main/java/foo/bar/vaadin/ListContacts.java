package foo.bar.vaadin;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Window;

import foo.bar.backend.Contact;
import foo.bar.backend.ContactNotFoundException;
import foo.bar.backend.ContactRepository;
import foo.bar.vaadin.ContactEditor.ContactAction;
import foo.bar.vaadin.ContactEditor.ContactActionListener;

@UIScoped
public class ListContacts extends CustomComponent  {

	@Inject
	ContactRepository contactRepository;

	Grid<Contact> contactsGrid = new Grid<>(Contact.class);

	public ListContacts() {
		init();
	}

	public void init() {

		setSizeFull();
		AbsoluteLayout absoluteLayout = new AbsoluteLayout();
		absoluteLayout.setSizeFull();
		setCompositionRoot(absoluteLayout);

		Button addButton = new Button("add");
		addButton.addClickListener(e -> addNew());
		absoluteLayout.addComponent(addButton, "top:20px;bottom:20px;left:20px;right:20px");

		contactsGrid.setSizeFull();
		absoluteLayout.addComponent(contactsGrid, "top:80px;bottom:20px;left:20px;right:20px");
		contactsGrid.addItemClickListener(e -> edit(e.getItem()));
	}
	
	@PostConstruct
	public void refresh() {
		contactsGrid.setItems(contactRepository.list());
	}

	public void addNew() {

		ContactEditor contactEditor = new ContactEditor();

		Window window = new Window("add contact", contactEditor);
		window.setModal(true);
		getUI().addWindow(window);
		contactEditor.setContact(new Contact());
		contactEditor.setContactActionListener((action, contact) -> {
			if (action == ContactAction.SAVE) {
				contactRepository.update(contact);
				refresh();

			}
			window.close();
			
		});
	}

	public void edit(Contact contactToEdit) {

		if (contactToEdit == null) {
			return;
		}
		ContactEditor contactEditor = new ContactEditor();

		Window window = new Window("edit contact "+contactToEdit.getId(), contactEditor);
		window.setModal(true);
		getUI().addWindow(window);
		contactEditor.setContact(contactToEdit);
		contactEditor.setContactActionListener((action, contact) -> {
			switch (action) {
			case NONE:
				break;
			case DELETE:
				try {
					contactRepository.delete(contact);
				} catch (ContactNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				refresh();
				break;
			case SAVE:
				contactRepository.update(contact);
				refresh();
				break;

			}
			window.close();
		});
	}
}
