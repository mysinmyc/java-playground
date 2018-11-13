import foo.bar.App;
import foo.bar.backend.Contact;
import foo.bar.backend.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =App.class)
@Transactional
public class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    @Test
    public void testInsert() {
        Long  countBefore= contactRepository.count();

        contactRepository.insertContact(new Contact("aaa","bbb","ccc"));

        Long countAfter =contactRepository.count();

        assertTrue(countBefore==countAfter-1);
    }

    @Test
    public  void testQuery() {

        Contact contactA= new Contact("aaa","aaa","aaa");
        contactRepository.insertContact(contactA);
        Contact contactB= new Contact("bbb","bbb","bbb");
        contactRepository.insertContact(contactB);

        List<Contact> contactList = contactRepository.list();

        assert(contactList.contains(contactA));
        assert(contactList.contains(contactB));

    }

    @Test
    public void testSingleObject() {
        Contact contactCiao = contactRepository.findById(1L);
        assertEquals(contactCiao.getName(),"CIAO");
    }

    @Test
    public void testDelete() {

        assert(contactRepository.count()>0);

        contactRepository.list().forEach(contactRepository::deleteContact);

        assert(contactRepository.count()==0);
    }

}
