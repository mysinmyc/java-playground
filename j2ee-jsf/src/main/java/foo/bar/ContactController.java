package foo.bar;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import foo.bar.backend.Contact;
import foo.bar.backend.ContactRepository;

@ManagedBean(name = "contactController")
public class ContactController {


	@Inject
	ContactBasket contactBasket;
	
	@Inject
	ContactRepository contactRepository;
	
	public String list() {
		return "list";
	}
	
	public String add() {
		contactBasket.setContact(new Contact());
		return "edit";
	}

	
	public String edit(Contact contact) {
		contactBasket.setContact(contact);
		return "edit";
	}
	
	public String save(Contact contact) {
		
		if (contact.getId()==null || contact.getId()==0) {
			contactRepository.insert(contact);
		}else {
			contactRepository.update(contact);
		}
		return "list";
	}
	
	public String delete(Contact contact) {
		contactRepository.delete(contact);
		return "list";
	}
}
