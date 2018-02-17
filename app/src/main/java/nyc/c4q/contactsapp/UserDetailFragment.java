package nyc.c4q.contactsapp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import nyc.c4q.contactsapp.model.UserDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailFragment extends Fragment {

    private TextView name, cell, email;
    private ImageView picture;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_detail, container, false);

        Bundle bundle= getArguments();
        UserDatabase user= null;

        setUpViews(view);

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(true)
                .build();

        if (bundle!=null) {
            user = (UserDatabase) bundle.getSerializable("user");
            String upperString = user.getFirstName().substring(0,1).toUpperCase() + user.getFirstName().substring(1) +
                    " " + user.getLastName().substring(0,1).toUpperCase() + user.getLastName().substring(1);
            name.setText(upperString);
            cell.setText(user.getCell());
            email.setText(user.getEmail());

            Picasso.with(view.getContext())
                    .load(user.getPicture())
                    .transform(transformation)
                    .into(picture);
        }

        return view;
    }

    public void setUpViews(View view) {
        name= view.findViewById(R.id.user_detail_name);
        cell= view.findViewById(R.id.user_detail_cell);
        email= view.findViewById(R.id.user_detail_email);
        picture= view.findViewById(R.id.user_detail_picture);
    }

}
