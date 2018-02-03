package nyc.c4q.contactsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.contactsapp.model.PersonName;
import nyc.c4q.contactsapp.model.PersonPicture;
import nyc.c4q.contactsapp.model.User_Schema;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


/**
 * Created by bobbybah on 1/30/18.
 */

public class User_Database extends SQLiteOpenHelper {

    private static final String DATABASENAME = "User.db";
    private static final int DATABASE_VERSION =1;

    private static User_Database instance;


    public static synchronized User_Database getInstance(Context context){
        if (instance == null) {
            instance = new User_Database(context.getApplicationContext());
        }
        return instance;
    }

    public User_Database(Context context) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
    }


    static {
         //registering our models into the database
        cupboard().register(User_Schema.class);
        cupboard().register(PersonName.class);
        cupboard().register(PersonPicture.class);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        cupboard().withDatabase(db).createTables();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        cupboard().withDatabase(db).upgradeTables();
    }
}
