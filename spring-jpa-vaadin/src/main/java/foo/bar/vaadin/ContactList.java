package foo.bar.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import foo.bar.backend.Contact;
import foo.bar.backend.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route( layout = MainLayout.class)
public class ContactList extends Div {

    @Autowired
    ContactRepository contactRepository;

    TextField txtField = new TextField();

    Grid<Contact> grid ;

    public ContactList() {

        add(txtField);
        txtField.setPlaceholder("Name pattern");
        txtField.setValueChangeMode(ValueChangeMode.EAGER);
        txtField.addValueChangeListener(e-> {
           load();
        });

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
        grid.setItems(txtField.isEmpty() ? contactRepository.findAll()
                : contactRepository.findByNameLike(txtField.getValue()));
    }

}
