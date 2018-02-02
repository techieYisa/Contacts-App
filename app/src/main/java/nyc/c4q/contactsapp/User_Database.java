package nyc.c4q.contactsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by bobbybah on 1/30/18.
 */

public class User_Database extends SQLiteOpenHelper {

    private static final String DATABASENAME = "User.db";
    private static final int DATABASE_VERSION =1;

    public User_Database(Context context) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
    }


//    static {
//         register our models
//        cupboard().register(User.class);
//    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        cupboard().withDatabase(db).upgradeTables();
    }
}
