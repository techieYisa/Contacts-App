package nyc.c4q.contactsapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.contactsapp.Interface.UserService;
import nyc.c4q.contactsapp.adapter.UserAdapter;
import nyc.c4q.contactsapp.model.UserDatabase;

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

