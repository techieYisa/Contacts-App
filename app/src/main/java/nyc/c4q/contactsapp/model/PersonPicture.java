package nyc.c4q.contactsapp.model;

/**
 * Created by yokilam on 2/3/18.
 */

public class PersonPicture {
    private long _id;
    public String large;
    public String medium;
    public String thumbnail;

    public long get_id() {
        return _id;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
