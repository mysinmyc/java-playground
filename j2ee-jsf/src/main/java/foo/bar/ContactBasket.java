package foo.bar;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import foo.bar.backend.Contact;

@Named("contactBasket")
@SessionScoped
public class ContactBasket implements Serializable {

	Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getContactLabel() {
		if (contact == null || contact.getId() == null || contact.getId() == 0) {
			return "New";
		}
		return contact.getName() + "(" + contact.getId() + ")";
	}
}
