package nyc.c4q.contactsapp;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

import nyc.c4q.contactsapp.Interface.UserService;
import nyc.c4q.contactsapp.Remote.RetrofitClient;
import nyc.c4q.contactsapp.adapter.UserAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nyc.c4q.contactsapp.MyJobScheduler.TAG;

/**
 * Created by yokilam on 2/14/18.
 */

public class RetrofitJob extends JobService {

    private UserService userService;
    private UserHelper db;

    @Override
    public boolean onStartJob(JobParameters params) {
        db= new UserHelper(this);
        userService = RetrofitClient.getRetrofit("https://randomuser.me/")
                .create(UserService.class);
        userService.getUserList().enqueue(new Callback<ArrayObject>() {

            @Override
            public void onResponse(Call<ArrayObject> call, Response<ArrayObject> response) {
                Toast.makeText(RetrofitJob.this, "It is running", Toast.LENGTH_SHORT).show();
                ArrayObject object = response.body();

                for (int i = 0; i < object.getResults().size(); i++) {
                    Log.d(TAG, "onResponse: " + object.getResults().get(i).getName().getFirst() );
                    db.addUser(object.getResults().get(i));
                }
            }

            @Override
            public void onFailure(Call <ArrayObject> call, Throwable t) {

                Log.d(TAG, t.getMessage());

            }
        });

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
