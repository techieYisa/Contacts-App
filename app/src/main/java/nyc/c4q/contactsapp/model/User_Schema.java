package nyc.c4q.contactsapp.model;

/**
 * Created by bobbybah on 1/30/18.
 */

public class User_Schema {
    public Long _id;
    public PersonName name;
    public String email;
    public String cell;
    public PersonPicture picture;

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


    public Long get_id() {
        return _id;
    }

    public PersonName getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    public PersonPicture getPicture() {
        return picture;
    }
}

