import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        return "Contact{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", address='" + address + '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", zip='" + zip + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", email='" + email + '\'' + '}';
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void displayContacts() {
        if(contacts.isEmpty()){
            System.out.println("There is no contacts in the the Address Book");
        }
        else{
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
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

public class AddressBookMain {

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Edit an existing contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewContact(addressBook, scanner);
                    break;
                case 2:
                    editExistingContact(addressBook, scanner);
                    break;
                case 3:
                    deleteContact(addressBook, scanner);
                    break;
                case 4:
                    addressBook.displayContacts();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private static void addNewContact(AddressBook addressBook, Scanner scanner) {
        System.out.println("\nEnter details for the new contact:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("State: ");
        String state = scanner.nextLine();

        System.out.print("ZIP Code: ");
        String zip = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        addressBook.addContact(newContact);

        System.out.println("Contact added successfully!");
    }

    private static void editExistingContact(AddressBook addressBook, Scanner scanner) {
        System.out.println("\nEnter the first name of the contact you want to edit:");
        String firstName = scanner.nextLine();
        System.out.println("Enter the last name of the contact:");
        String lastName = scanner.nextLine();

        Contact existingContact = addressBook.findContactByName(firstName, lastName);
        if (existingContact == null) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("\nExisting Contact Details:");
        System.out.println(existingContact);

        System.out.println("\nEnter new details (leave blank to keep current value):");
        System.out.print("First Name: ");
        String newFirstName = scanner.nextLine();
        if (!newFirstName.isEmpty()) {
            existingContact.setFirstName(newFirstName);
        }
        System.out.print("Last Name: ");
        String newLastName = scanner.nextLine();
        if (!newLastName.isEmpty()) {
            existingContact.setLastName(newLastName);
        }
        System.out.print("Address: ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            existingContact.setAddress(newAddress);
        }
        System.out.print("City: ");
        String newCity = scanner.nextLine();
        if (!newCity.isEmpty()) {
            existingContact.setCity(newCity);
        }
        System.out.print("State: ");
        String newState = scanner.nextLine();
        if (!newState.isEmpty()) {
            existingContact.setState(newState);
        }
        System.out.print("ZIP Code: ");
        String newZip = scanner.nextLine();
        if (!newZip.isEmpty()) {
            existingContact.setZip(newZip);
        }
        System.out.print("Phone Number: ");
        String newPhoneNumber = scanner.nextLine();
        if (!newPhoneNumber.isEmpty()) {
            existingContact.setPhoneNumber(newPhoneNumber);
        }
        System.out.print("Email: ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            existingContact.setEmail(newEmail);
        }

        System.out.println("Contact updated successfully!");
    }

    private static void deleteContact(AddressBook addressBook, Scanner scanner) {
        System.out.println("\nEnter the first name of the contact you want to delete:");
        String firstName = scanner.nextLine();
        System.out.println("Enter the last name of the contact:");
        String lastName = scanner.nextLine();

        boolean deleted = addressBook.deleteContact(firstName, lastName);
        if (deleted) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

}
