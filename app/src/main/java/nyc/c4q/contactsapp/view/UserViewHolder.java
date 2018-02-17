package nyc.c4q.contactsapp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import nyc.c4q.contactsapp.R;
import nyc.c4q.contactsapp.UserDetailFragment;
import nyc.c4q.contactsapp.model.UserDatabase;


/**
 * Created by c4q on 2/2/18.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public ImageView userPic;
    Context context;

    public UserViewHolder(View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.name_textview);
        userPic = itemView.findViewById(R.id.profile_image);
        context= itemView.getContext();
    }
    public void onBind(final UserDatabase user){
        String upperString = user.getFirstName().substring(0,1).toUpperCase() + user.getFirstName().substring(1) +
                " " + user.getLastName().substring(0,1).toUpperCase() + user.getLastName().substring(1);

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(true)
                .build();

        userName.setText(upperString);
        Picasso.with(itemView.getContext())
                .load(user.getPicture())
                .transform(transformation)
                .into(userPic);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                UserDetailFragment detailFragment = new UserDetailFragment();
                detailFragment.setArguments(bundle);
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("UserList").replace(R.id.fragment_container, detailFragment);
                fragmentTransaction.commit();
            }
        });

    }
}
