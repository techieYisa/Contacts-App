package nyc.c4q.contactsapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.contactsapp.R;
import nyc.c4q.contactsapp.User;
import nyc.c4q.contactsapp.UserDatabase;
import nyc.c4q.contactsapp.model.User_Schema;

/**
 * Created by c4q on 2/2/18.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public ImageView userPic;

    public UserViewHolder(View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.name_textview);
        userPic = itemView.findViewById(R.id.profile_image);
    }
    public void onBind(UserDatabase user){
        StringBuilder fullName = new StringBuilder();
        fullName.append(user.getFirstName()).append(" ").append(user.getLastName());

        userName.setText(fullName.toString());
        Picasso.with(itemView.getContext())
                .load(user.getPicture())
                .into(userPic);
    }
}
