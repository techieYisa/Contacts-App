package nyc.c4q.contactsapp.model;

/**
 * Created by yokilam on 2/3/18.
 */

public class PersonName {
    private long _id;
    public String title;
    public String first;
    public String last;

    public long get_id() {
        return _id;
    }

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
