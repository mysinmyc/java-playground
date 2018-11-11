package foo.bar.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import foo.bar.backend.Contact;
import foo.bar.backend.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route( layout = MainLayout.class)
public class ContactList extends Div {

    @Autowired
    ContactRepository contactRepository;


    Grid<Contact> grid ;

    public ContactList() {


        grid =new Grid<>(Contact.class);
        grid.addSelectionListener(e-> {

            Contact selectedContact = e.getFirstSelectedItem().orElse(null);

            if (selectedContact!=null) {
                getUI().get().navigate(ContactEditor.class,selectedContact.getId());
            }
        });
        add(grid);
    }

    @PostConstruct
    public void load() {
        grid.setItems(contactRepository.findAll());
    }

}
