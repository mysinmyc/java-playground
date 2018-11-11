package foo.bar;

class ContactNotFoundException extends Exception {


    public ContactNotFoundException(Long id) {
        super("No contact with id "+id);
    }
}
