package nyc.c4q.contactsapp;

/**
 * Created by bobbybah on 1/30/18.
 */

public class User_Schema {

    public User_Schema(){

         this.name = new PersonName();
         this.email = "noEmail";
         this.cell = "noCell";
         this.picture = new PersonPicture();
    }


    public User_Schema(Long _id, PersonName name, String email, String cell, PersonPicture picture){
        this._id = _id;
        this.name = name;
        this.email = email;
        this.cell = cell;
        this.picture = picture;

    }

    public Long _id;
    public PersonName name;
    public String email;
    public String cell;
    public PersonPicture picture;

    public class PersonName {
        public String title;
        public String first;
        public String last;
    }


    public class PersonPicture {
        public String large;
        public String medium;
        public String thumbnail;

    }


}

