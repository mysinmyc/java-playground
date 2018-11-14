package foo.bar.vaadin;

import javax.inject.Inject;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@CDIUI("")
@Push
@Theme("valo")
public class ContactUI extends UI {
	

	@Inject
	ListContacts listContacts;
 
    
	@Override
	protected void init(VaadinRequest request) {
		setContent(listContacts);
	}

}
