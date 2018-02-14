package nyc.c4q.contactsapp;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

/**
 * Created by yokilam on 2/14/18.
 */

public class MyJobScheduler {
    public static final String TAG = MyJobScheduler.class.getSimpleName();
    public static final int RETROFIT_JOB_ID = 666;

    public static void start(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        buildRetrofitJob(context, jobScheduler);
    }

    public static void buildRetrofitJob(Context applicationContext, JobScheduler jobScheduler) {
        Log.d(TAG, "buildRetrofitJob called");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            JobInfo.Builder retrofitJob = new JobInfo
                    .Builder(RETROFIT_JOB_ID, new ComponentName(applicationContext, RetrofitJob.class.getName()))
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

            jobScheduler.schedule(retrofitJob.build());
        }
    }
}
