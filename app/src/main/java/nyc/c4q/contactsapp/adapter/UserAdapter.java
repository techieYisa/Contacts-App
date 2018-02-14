package nyc.c4q.contactsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import nyc.c4q.contactsapp.R;
import nyc.c4q.contactsapp.User;
import nyc.c4q.contactsapp.UserDatabase;
import nyc.c4q.contactsapp.model.User_Schema;
import nyc.c4q.contactsapp.view.UserViewHolder;

/**
 * Created by c4q on 2/2/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    List<UserDatabase> userList;

    public UserAdapter(List <UserDatabase> userList) {
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item_view, parent, false);
        return new UserViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
       holder.onBind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
