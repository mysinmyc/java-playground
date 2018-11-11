package foo.bar.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import foo.bar.backend.Contact;
import foo.bar.backend.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Route(layout = MainLayout.class)
public class ContactEditor extends Div  implements HasUrlParameter<Long> {

    Contact currentContact;

    Binder<Contact> binder = new Binder<>(Contact.class);

    Button deleteButton = new Button("delete");
    TextField name = new TextField("name");
    TextField address = new TextField("address");
    TextField number = new TextField("number");

    @Autowired
    ContactRepository contactRepository;

    public ContactEditor() {
        binder.bindInstanceFields(this);

        FormLayout formLayout = new FormLayout();
        add(formLayout);
        formLayout.add(name,address,number);
        add(new Button("save", e-> save()));

        deleteButton.addClickListener(e->delete());
        add(deleteButton);
    }

    private void delete() {
        contactRepository.delete(currentContact);
        getUI().get().navigate(ContactList.class);
    }

    private void save() {
        try {
            binder.writeBean(currentContact);
        } catch (ValidationException e) {
            Notification.show("ERROR "+e.getMessage());
            return;

        }
        contactRepository.save(currentContact);

        getUI().get().navigate(ContactList.class);
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Long contactId) {

        if (contactId==null) {
            currentContact = new Contact();
        } else {
            currentContact = contactRepository.findById(contactId).orElse(new Contact());
        }
        deleteButton.setVisible(currentContact.getId()!=null && currentContact.getId()>0);
        binder.readBean(currentContact);
    }
}
