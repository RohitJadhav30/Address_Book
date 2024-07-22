import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Map<String, List<Contact>> cityDictionary;
    private Map<String, List<Contact>> stateDictionary;



    public AddressBook() {
        this.contacts = new ArrayList<>();
        this.cityDictionary = new HashMap<>();
        this.stateDictionary = new HashMap<>();
    }

    public void addContact(Contact contact) {
        if(contacts.stream().anyMatch(c -> c.equals(contact))){
            System.out.println("Duplicate Entry. Contact already exists");
        }
        else{
            contacts.add(contact);
            addToDictionary(contact);
            System.out.println("Contact added succesfully!");
        }
    }

    public void addToDictionary(Contact contact){
        cityDictionary.computeIfAbsent(contact.getCity(), k -> new ArrayList<>()).add(contact);
        stateDictionary.computeIfAbsent(contact.getState(), k -> new ArrayList<>()).add(contact);
    }

    public void updateContact(Contact oldContact, Contact newContact){
        if(contacts.remove(oldContact)){
            contacts.add(newContact);
            updateDictionary(oldContact, newContact);
            System.out.println("Contact updated succesfully");
        }else{
            System.err.println("Contact not found");
        }
    }

    private void updateDictionary(Contact oldContact, Contact newContact){
        cityDictionary.getOrDefault(oldContact.getCity(), new ArrayList<>()).remove(oldContact);
        stateDictionary.getOrDefault(oldContact.getState(), new ArrayList<>()).remove(oldContact);
        addToDictionary(newContact);
    }

    public List<Contact> getContactByCity(String city){
        return cityDictionary.getOrDefault(city, Collections.emptyList());
    }

    public List<Contact> getContactByState(String state){
        return stateDictionary.getOrDefault(state, Collections.emptyList());
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
