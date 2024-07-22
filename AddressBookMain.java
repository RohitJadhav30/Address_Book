import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() { 
        return firstName; 
    }

    public void setFirstName(String firstName) {
         this.firstName = firstName; 
    }

    public String getLastName() {
         return lastName; 
    }

    public void setLastName(String lastName) {
         this.lastName = lastName;
    }

    public String getAddress() {
         return address; 
    }

    public void setAddress(String address) {
         this.address = address; 
    }

    public String getCity() {
         return city; 
    }

    public void setCity(String city) {
         this.city = city; 
    }

    public String getState() {
         return state; 
    }

    public void setState(String state) {
         this.state = state; 
    }

    public String getZip() {
         return zip; 
    }

    public void setZip(String zip) {
         this.zip = zip; 
    }

    public String getPhoneNumber() {
         return phoneNumber; 
    }

    public void setPhoneNumber(String phoneNumber) {
         this.phoneNumber = phoneNumber; 
    }

    public String getEmail() {
         return email; 
    }

    public void setEmail(String email) {
         this.email = email; 
    }

    @Override
    public String toString() {
        return "## " + "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' ;
    }

    @Override
    public boolean equals(Object obj) {
        if(this ==obj)
            return true;

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Contact contact = (Contact)obj;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        if(contacts.stream().anyMatch(c -> c.equals(contact))){
            System.out.println("Duplicate Entry. Contact already exists");
        }
        else{
            contacts.add(contact);
            System.out.println("Contact added succesfully!");
        }
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("There are no contacts in the Address Book.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public List<Contact> geContacts(){
        return contacts;
    }

    public Contact findContactByName(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName) && contact.getLastName().equalsIgnoreCase(lastName)) {
                return contact;
            }
        }
        return null;
    }

    public boolean deleteContact(String firstName, String lastName) {
        Contact contactToRemove = null;
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName) && contact.getLastName().equalsIgnoreCase(lastName)) {
                contactToRemove = contact;
                break;
            }
        }
        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
            return true;
        }
        return false;
    }
}
