package foo.bar.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ContactRepository {


    static Logger LOGGER = LoggerFactory.getLogger(ContactRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long insertContact(Contact contact) {
        jdbcTemplate.update ("INSERT INTO CONTACT (NAME,ADDRESS,NUMBER) VALUES(?,?,?)", contact.getName(),contact.getAddress(),contact.getNumber());

        //don't use in production
        Long lastId = jdbcTemplate.queryForObject("SELECT MAX(ID) FROM CONTACT",Long.class);
        contact.setId(lastId);
        LOGGER.debug("Inserted contact {} ",contact);

        return lastId;
    }

    public Long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM CONTACT",Long.class);
    }

    public void deleteContact(Contact contact) {
        LOGGER.debug("Deleting contact {} ",contact);
        jdbcTemplate.update("DELETE FROM CONTACT WHERE ID=?",contact.getId());

    }

    public Contact findById(Long id) {
        LOGGER.debug("Searching contact by id {} ",id);
        return jdbcTemplate.queryForObject(
                "SELECT ID,NAME,ADDRESS,NUMBER FROM CONTACT WHERE ID=?",  (resultSet, rowNum) -> {
                    Contact currentContact = new Contact(resultSet.getLong("ID"));
                    currentContact.setName(resultSet.getString("NAME"));
                    currentContact.setName(resultSet.getString("ADDRESS"));
                    currentContact.setName(resultSet.getString("NUMBER"));
                    return currentContact;
                },id);
    }

    public List<Contact> list() {
        return jdbcTemplate.query("SELECT ID,NAME,ADDRESS,NUMBER FROM CONTACT",  (resultSet, rowNum) -> {
            Contact currentContact = new Contact(resultSet.getLong("ID"));
            currentContact.setName(resultSet.getString("NAME"));
            currentContact.setName(resultSet.getString("ADDRESS"));
            currentContact.setName(resultSet.getString("NUMBER"));
            LOGGER.debug("Returned contact {} in list",currentContact);
            return currentContact;

        });
    }

}
