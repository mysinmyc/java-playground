package foo.bar;

import foo.bar.backend.Contact;
import foo.bar.backend.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;

    @RequestMapping("/contacts")
    public List<Contact> list() {
        return contactRepository.findAll();
    }

    @GetMapping("/contacts/{id}")
    public Contact getById(@PathVariable Long id) throws ContactNotFoundException {
        return contactRepository.findById(id).orElseThrow( ()->new ContactNotFoundException(id));
    }

    @DeleteMapping("/contacts/{id}")
    public void deleteById(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }

    @PostMapping("/contacts")
    public Contact create(@RequestBody Contact contact) {
        return contactRepository.save(contact);

    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Contact contact, @PathVariable Long id) {

        if (!contact.getId().equals(id)) {
            throw new RuntimeException("????");
        }

        Optional<Contact> contactOptional = contactRepository.findById(id);

        if (!contactOptional.isPresent())
            return ResponseEntity.notFound().build();


        contactRepository.save(contact);

        return ResponseEntity.noContent().build();
    }
}
