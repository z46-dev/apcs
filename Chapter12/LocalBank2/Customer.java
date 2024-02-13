package LocalBank2;

import java.io.Serializable;

public class Customer implements Serializable {
    private String firstName, lastName;

    public Customer(String fName, String lName) {
        firstName = fName;
        lastName = lName;
    }

    public String toString() {
        String custString;

        custString = firstName + " " + lastName + "\n";
        return (custString);
    }
}