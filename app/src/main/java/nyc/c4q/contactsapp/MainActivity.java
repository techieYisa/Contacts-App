package nyc.c4q.contactsapp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.contactsapp.Interface.UserService;
import nyc.c4q.contactsapp.Remote.RetrofitClient;
import nyc.c4q.contactsapp.adapter.UserAdapter;
import nyc.c4q.contactsapp.model.PersonName;
import nyc.c4q.contactsapp.model.PersonPicture;
import nyc.c4q.contactsapp.model.User_Schema;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;
import static nyc.c4q.contactsapp.MyJobScheduler.TAG;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Testing";
    private UserHelper db;
    RecyclerView contactRecyclerView;
    private UserService userService;

    private List<UserDatabase> userList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new UserHelper(this);

        if (db.isDatabaseEmpty()) {
            MyJobScheduler.start(getApplicationContext());
            userList=db.getUserList();
            Log.d(TAG, "onCreate: " + userList.size());
        } else {
            userList=db.getUserList();
            Log.d(TAG, "onCreate: " + userList.size());
        }

        contactRecyclerView = findViewById(R.id.user_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        UserAdapter userAdapter = new UserAdapter(userList);
        contactRecyclerView.setAdapter(userAdapter);
        contactRecyclerView.setLayoutManager(linearLayoutManager);
    }
}

