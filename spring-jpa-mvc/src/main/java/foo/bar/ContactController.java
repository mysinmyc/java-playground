package foo.bar;

import foo.bar.backend.Contact;
import foo.bar.backend.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/contacts")
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "contact/list";
    }

    @RequestMapping("/contacts/_add")
    public String addContact(Model model) {
        model.addAttribute("contact",new Contact());
        return "contact/edit";
    }

    @RequestMapping("/contacts/{id}")
    public String editContact(@PathVariable("id") Long id,Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("contact not found, id: "+id));
        model.addAttribute("contact", contact);
        return "contact/edit";
    }

    @PostMapping("/contacts")
    public String updateContact(@Valid @ModelAttribute Contact contact,
                                BindingResult result) {
        contactRepository.save(contact);
        return "redirect:/contacts";
    }

    @RequestMapping("/contacts/{id}/_delete")
    public String deleteContact(@PathVariable("id") Long id, Model model) {
        contactRepository.deleteById(id);
        return "redirect:/contacts";
    }

}
