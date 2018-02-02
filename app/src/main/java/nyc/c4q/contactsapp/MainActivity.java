package nyc.c4q.contactsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.contactsapp.Interface.UserService;
import nyc.c4q.contactsapp.Remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserService userService;
    List<User> userList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView contactRecyclerView = (RecyclerView) findViewById(R.id.user_recyclerview);

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
