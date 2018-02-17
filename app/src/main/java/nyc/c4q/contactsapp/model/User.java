package nyc.c4q.contactsapp.model;

/**
 * Created by yokilam on 1/24/18.
 */

public class User {

    private PersonName name;
    private PersonAddress location;
    private String email;
    private String cell;
    private PersonPicture picture;

    public User(PersonName name, PersonAddress location, String email, String cell, PersonPicture picture) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.cell = cell;
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    public PersonName getName() {
        return name;
    }

    public PersonAddress getLocation() {
        return location;
    }

    public PersonPicture getPicture() {
        return picture;
    }

    public class PersonName {
        private String title;
        private String first;
        private String last;

        public String getTitle() {
            return title;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }


    public class PersonAddress {
        private String street;
        private String city;
        private String state;
        private Integer postcode;

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public Integer getPostcode() {
            return postcode;
        }
    }


    public class PersonPicture {
        private String large;
        private String medium;
        private String thumbnail;

        public String getLarge() {
            return large;
        }

        public String getMedium() {
            return medium;
        }

        public String getThumbnail() {
            return thumbnail;
        }
    }
}
