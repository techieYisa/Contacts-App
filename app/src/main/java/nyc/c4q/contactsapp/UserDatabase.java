package nyc.c4q.contactsapp;

/**
 * Created by yokilam on 2/14/18.
 */

public class UserDatabase {
    private String FirstName;
    private String LastName;
    private String Email;
    private String Cell;
    private String picture;

    public UserDatabase(String firstName, String lastName, String email, String cell, String picture) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Cell = cell;
        this.picture = picture;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getCell() {
        return Cell;
    }

    public String getPicture() {
        return picture;
    }
}
