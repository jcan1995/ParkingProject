package com.example.bruhshua.parking.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bruhshua.parking.Model.Course;
import com.example.bruhshua.parking.Model.Singleton;
import com.example.bruhshua.parking.Model.Student;
import com.example.bruhshua.parking.R;

import java.util.ArrayList;

/**
 * Created by bruhshua on 4/24/17.
 */

public class ProfileFragment extends Fragment {

    private Button bViewSchedule;
    private ArrayList<Course> courses = new ArrayList<>();//Temporary till singleton made
    private ImageView ivProfilePicture;

    public static ProfileFragment newInstance(){
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle args = new Bundle();
      //  args.putSerializable("USER",student);
        profileFragment.setArguments(args);

        return profileFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        courses = Singleton.getInstance(getActivity().getApplicationContext()).getStudent().getCourses();
        bViewSchedule = (Button) v.findViewById(R.id.bToSchedule);
        bViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ScheduleFragment.class);
                intent.putExtra("COURSES",courses);
                startActivity(intent);

            }
        });

        ivProfilePicture = (ImageView) v.findViewById(R.id.ivPicture);
        ivProfilePicture.setImageResource(R.drawable.profile_picture);


        return v;
    }
}
