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

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.contactsapp.Interface.UserService;
import nyc.c4q.contactsapp.Remote.RetrofitClient;
import nyc.c4q.contactsapp.adapter.UserAdapter;
import nyc.c4q.contactsapp.model.User_Schema;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HELP!!";
    private UserService userService;
    private SQLiteDatabase user_db;

    List<User_Schema> userList= new ArrayList<>();
    Context context;

//    User_Schema user = new User_Schema();

    //TODO: Instantiate database to be used in cupboard.
    //TODO: Put data from retrofit call into DB.
    //TODO: Create method if database is != null use data from database instead of retrofit call
    //TODO: Create a user details activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User_Database userHelper= User_Database.getInstance(this);
        user_db = userHelper.getWritableDatabase();

//        long id = cupboard().withDatabase(user_db.getReadableDatabase()).put(user);
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
                for (int i= 0; i < userList.size(); i ++) {
                    addUser(userList.get(i));
                }
                contactRecyclerView.setAdapter(new UserAdapter(selectAllUser(), context));
              Log.d(TAG, "onResponse " + userList);
            }

            @Override
            public void onFailure(Call <ArrayObject> call, Throwable t) {

                Log.d(TAG, "onFailure " );

            }
        });

    }

    private void addUser(User_Schema user) {
        cupboard().withDatabase(user_db).put(user);
    }

    private List<User_Schema> selectAllUser(){
        List <User_Schema> users= new ArrayList <>();

        try{
            QueryResultIterable <User_Schema> itr= cupboard().withDatabase(user_db).query(User_Schema.class).query();
            for (User_Schema user: itr) {
                users.add(user);
            }
            itr.close();
        } catch (Exception e) {
            Log.e(TAG, "selectAllUser: " + e );
        }
            Log.e(TAG, "selectAllUser: " + users + users.get(0).get_id() + users.get(0).getName() );
        return users;
    }
}

