package foo.bar;

class ContactNotFoundException extends Exception {


    public ContactNotFoundException(String id) {
        super("No contact with id "+id);
    }
}
