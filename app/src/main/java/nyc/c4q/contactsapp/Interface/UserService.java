package nyc.c4q.contactsapp.Interface;

import nyc.c4q.contactsapp.ArrayObject;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yokilam on 1/28/18.
 */

public interface UserService {

    @GET("api/?nat=us&inc=name,location,cell,email,dob,picture&results=20")
    Call<ArrayObject> getUserList();
}
