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
import nyc.c4q.contactsapp.view.UserViewHolder;

/**
 * Created by c4q on 2/2/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context context;
    List<User> userList;


    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item_view, parent, false);
        context = parent.getContext();
        return new UserViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        User user = userList.get(position);
        holder.onBind(user, context);

        Picasso.with(context)
                .load(user.getPicture()
                        .getThumbnail())
                .centerInside()
                .resize(500, 500)
                .into(holder.userPic);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
