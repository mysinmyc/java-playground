package foo.bar.backend;

public class Contact {


    public Contact() {

    }

    public Contact(Long id) {
        this.id = id;
    }

    public Contact( String name, String address, String number) {
        this(-1L,name,address,number);
    }


    public Contact(Long id, String name, String address, String number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Long id;

    String name;

    String address;

    String number;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj ==null || !(obj instanceof Contact) || getId()==null) {
            return false;
        }
        return getId().equals(((Contact) obj).getId());
    }

    @Override
    public String toString() {
        return "[CONTACT id:"+id+" name: "+name+" address: "+address+" number: "+number+"]";
    }
}
