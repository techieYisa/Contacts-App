package nyc.c4q.contactsapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.contactsapp.R;
import nyc.c4q.contactsapp.User;

/**
 * Created by c4q on 2/2/18.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public ImageView userPic;

    public UserViewHolder(View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.userinfo_textview);
        userPic = itemView.findViewById(R.id.user_imageview);
    }
    public void onBind(User user, Context context){
        String fullName = user.getName().getTitle();
        StringBuilder sb = new StringBuilder(fullName);
        sb.append(" ").append(user.getName().getFirst());
        sb.append(" ").append(user.getName().getLast());

        userName.setText(sb.toString());
    }
}
