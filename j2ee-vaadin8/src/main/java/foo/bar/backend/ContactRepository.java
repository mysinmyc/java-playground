package foo.bar.backend;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class ContactRepository {

    @PersistenceContext
    EntityManager entityManager;


    public List<Contact> list() {
        return entityManager.createQuery( "select c from "+
                entityManager.getMetamodel().entity(Contact.class).getName()+" c", Contact.class).getResultList();
    }

    public void insert(Contact contact) {
        entityManager.persist(contact);
    }

    public void delete(Contact contact) throws ContactNotFoundException {
    	
    	if (entityManager.contains(contact)==false) {
    		contact = findById(contact.getId());
    	}
        entityManager.remove(contact);
    }

    public void update(Contact contact) {
        entityManager.merge(contact);
    }

    public Contact findById(Long id) throws ContactNotFoundException {
        Contact foundContact =entityManager.find(Contact.class,id);
        if (foundContact==null) {
        	throw new ContactNotFoundException("no contact with id "+id);
        }
        return foundContact;
    }
}
