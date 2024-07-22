import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookManager {
    private static Map<String, AddressBook> addressBookMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new Address Book");
            System.out.println("2. Select an Address Book");
            System.out.println("3. Show all Address Books");
            System.out.println("4. Search for Person using City/State");
            System.err.println("5. view person by city/state");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewAddressBook();
                    break;
                case 2:
                    selectAddressBook();
                    break;
                case 3:
                    showAllAddressBooks();
                    break;
                case 4:
                    searchByCityOrState();
                    break;
                case 5:
                    viewPersonByCityOrState();
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private static void viewPersonByCityOrState(){
        System.out.print("Enter 'city' to view by city or 'state' to view by state: ");
        String option = scanner.nextLine().trim().toLowerCase();

        switch (option) {
            case "city":
                System.out.print("Enter the city: ");
                String city = scanner.nextLine();
                List<Contact> contactsByCity = addressBookMap.values().stream().flatMap(addressBook -> addressBook.getContactByCity(city).stream()).collect(Collectors.toList());

                if(contactsByCity.isEmpty()){
                    System.out.println("No contacts found in specified city");
                }
                else{
                    System.out.println("Contacts in city: " + city);
                    contactsByCity.forEach(System.out::println);
                }
                break;

                case "state":
                System.out.print("Enter the city: ");
                String state = scanner.nextLine();
                List<Contact> contactsByState = addressBookMap.values().stream().flatMap(addressBook -> addressBook.getContactByState(state).stream()).collect(Collectors.toList());

                if(contactsByState.isEmpty()){
                    System.out.println("No contacts found in specified city");
                }
                else{
                    System.out.println("Contacts in city: " + state);
                    contactsByState.forEach(System.out::println);
                }
                break;
        
            default:
            System.out.println("invalid option");
        }
        System.out.println("returning to main menu");
    }

    private static void addNewAddressBook() {
        System.out.print("\nEnter the name of the new Address Book: ");
        String addressBookName = scanner.nextLine();
        if (addressBookMap.containsKey(addressBookName)) {
            System.out.println("An Address Book with this name already exists.");
        } else {
            addressBookMap.put(addressBookName, new AddressBook());
            System.out.println("Address Book added successfully!");
        }
    }

    private static void selectAddressBook() {
        System.out.print("\nEnter the name of the Address Book to select: ");
        String addressBookName = scanner.nextLine();
        AddressBook selectedAddressBook = addressBookMap.get(addressBookName);
        if (selectedAddressBook == null) {
            System.out.println("Address Book not found.");
        } else {
            manageAddressBook(selectedAddressBook, addressBookName);
        }
    }

    private static void manageAddressBook(AddressBook addressBook, String addressBookName) {
        while (true) {
            System.out.println("\nManage Address Book: " + addressBookName);
            System.out.println("Choose an option:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Edit an existing contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Back to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewContact(addressBook);
                    break;
                case 2:
                    editExistingContact(addressBook);
                    break;
                case 3:
                    deleteContact(addressBook);
                    break;
                case 4:
                    addressBook.displayContacts();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private static void showAllAddressBooks() {
        if (addressBookMap.isEmpty()) {
            System.out.println("No Address Books available.");
        } else {
            for (Map.Entry<String, AddressBook> entry : addressBookMap.entrySet()) {
                String addressBookName = entry.getKey();
                AddressBook addressBook = entry.getValue();
                System.out.println("\nAddress Book: " + addressBookName);
                addressBook.displayContacts();
            }
        }
    }

    private static void addNewContact(AddressBook addressBook) {
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
    }

    private static void editExistingContact(AddressBook addressBook) {
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

    private static void deleteContact(AddressBook addressBook) {
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

    private static void searchByCityOrState(){
        System.out.println("Search for person by city or state");
        System.out.print("Enter the city: ");
        String city = scanner.nextLine();
        System.out.print("Enter the state: ");
        String state = scanner.nextLine();

        List<Contact> result = addressBookMap.values().stream().flatMap(addressBook -> addressBook.geContacts().stream()).filter(contact -> contact.getCity().equalsIgnoreCase(city) || contact.getState().equalsIgnoreCase(state)).collect(Collectors.toList());

        if(result.isEmpty()){
            System.out.println("No contacts found in the specified city or state.");
        }
        else{
            System.out.println("Search result: ");
            result.forEach(System.out::println);
        }
        System.out.println("Returning to main menu...");
    }
}
