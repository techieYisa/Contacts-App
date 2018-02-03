package nyc.c4q.contactsapp;


import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.Cupboard;
import nyc.c4q.contactsapp.Interface.UserService;
import nyc.c4q.contactsapp.Remote.RetrofitClient;
import nyc.c4q.contactsapp.adapter.UserAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HELP!!";
    private UserService userService;
    List<User> userList= new ArrayList<>();
    Context context;

    User_Database user_db = new User_Database(this);
    User_Schema user = new User_Schema();
    long id = cupboard().withDatabase(user_db.getReadableDatabase()).put(user);

    //TODO: Instantiate database to be used in cupboard.
    //TODO: Put data from retrofit call into DB.
    //TODO: Create method if database is != null use data from database instead of retrofit call
    //TODO: Create a user details activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView contactRecyclerView = findViewById(R.id.user_recyclerview);
        UserAdapter userAdapter = new UserAdapter(userList, context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        contactRecyclerView.setAdapter(userAdapter);
        contactRecyclerView.setLayoutManager(linearLayoutManager);

        userService= RetrofitClient.getRetrofit("https://randomuser.me/")
                .create(UserService.class);
        userService.getUserList().enqueue(new Callback <ArrayObject>() {


            @Override
            public void onResponse(Call<ArrayObject> call, Response<ArrayObject> response) {
                ArrayObject object= response.body();

                userList = object.getResults();
                contactRecyclerView.setAdapter(new UserAdapter(userList, context));
              Log.d(TAG, "onResponse " + userList);

            }

            @Override
            public void onFailure(Call <ArrayObject> call, Throwable t) {

                Log.d(TAG, "onFailure " );

            }
        });

    }
}
