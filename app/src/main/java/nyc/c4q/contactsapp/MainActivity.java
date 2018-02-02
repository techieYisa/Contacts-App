package nyc.c4q.contactsapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.Cupboard;
import nyc.c4q.contactsapp.Interface.UserService;
import nyc.c4q.contactsapp.Remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {

    private UserService userService;
    List<User> userList= new ArrayList<>();


    //User_Schema user_DB = cupboard().withDatabase()

    //TODO: Instantiate database to be used in cupboard.
    //TODO: Put data from retrofit call into DB.
    //TODO: Create method if database is != null use data from database instead of retrofit call
    //TODO: Create a user details activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userService= RetrofitClient.getRetrofit("https://randomuser.me/").create(UserService.class);
        userService.getUserList().enqueue(new Callback <ArrayObject>() {
            @Override
            public void onResponse(Call<ArrayObject> call, Response<ArrayObject> response) {
                ArrayObject object= response.body();
                userList=object.getResults();
            }

            @Override
            public void onFailure(Call <ArrayObject> call, Throwable t) {

            }
        });




    }
}
