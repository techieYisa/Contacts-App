package nyc.c4q.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by yokilam on 2/3/18.
 */

public class UserHelper extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "User_table";
    private static final int DATABASE_VERSION =1;
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    public static final String COL3= "email";
    public static final String COL4= "cell";
    public static final String COL5= "picture";

    public UserHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE" + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FirstName text,LastName text, Email text, Cell text, Picture text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addUser (User user) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM User_table" +
                " WHERE FirstName = '" + user.getName().getFirst() + "' AND LastName = '" + user.getName().getLast()+ "';", null);
        if (cursor.getCount()== 0) {
            ContentValues values= new ContentValues();
            values.put("FirstName", user.getName().getFirst());
            values.put("LastName", user.getName().getLast());
            values.put("Email", user.getEmail());
            values.put("Cell", user.getCell());
            values.put("Picture", user.getPicture().getLarge());
            this.getWritableDatabase().insert(TABLE_NAME, null, values);
        }
    }

    public List<UserDatabase> getUserList(){
        List<UserDatabase> userList= new ArrayList <>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM User_table;", null);
        if (cursor!= null) {
            if(cursor.moveToFirst()){
                do {
                    UserDatabase user = new UserDatabase(
                            cursor.getString(cursor.getColumnIndex("FirstName")),
                            cursor.getString(cursor.getColumnIndex("LastName")),
                            cursor.getString(cursor.getColumnIndex("Email")),
                            cursor.getString(cursor.getColumnIndex("Cell")),
                            cursor.getString(cursor.getColumnIndex("Picture")));
                    userList.add(user);
                } while (cursor.moveToNext());
            }
        }
        return userList;
    }

    public boolean isDatabaseEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query= "SELECT * FROM " + TABLE_NAME;
        Cursor data= db.rawQuery(query, null);
        Log.d(TAG, "isDatabaseEmpty: " + data.getCount());
        if(data.getCount() == 0) {
            Log.d("isDatabaseEmpty: ", "IS EMPTY");
            return true;
        }
        return false;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "SELECT * FROM " + TABLE_NAME;
        Cursor data= db.rawQuery(query, null);
        return data;
    }



}
