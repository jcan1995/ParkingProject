package com.example.bruhshua.parking.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruhshua.parking.Model.Course;
import com.example.bruhshua.parking.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruhshua on 4/24/17.
 */

public class ScheduleFragment extends AppCompatActivity {

    private ListView listview;
    private ArrayList<Course> userCourses = new ArrayList<>();

 /*   public static ScheduleFragment newInstance(ArrayList<Course> courses){

        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putSerializable("COURSES", courses);
        fragment.setArguments(args);
        return fragment;
    }*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_schedule_list_view);
        userCourses = (ArrayList<Course>) getIntent().getSerializableExtra("COURSES");
        listview = (ListView) findViewById(R.id.listView);
        ScheduleAdapter adapter = new ScheduleAdapter(userCourses);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule_list_view,container,false);
        listview = (ListView) v.findViewById(R.id.listView);
        ScheduleAdapter adapter = new ScheduleAdapter(userCourses);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



        return v;
    }*/


    public class ScheduleAdapter extends ArrayAdapter<Course>{

        public ScheduleAdapter(ArrayList<Course> course) {
            super(getApplicationContext(), android.R.layout.simple_list_item_1, course);
        }

        @Override
        public int getPosition(Course item) {
            return super.getPosition(item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.fragment_schedule_list_view_item,null);

            Course course = getItem(position);
            if(course != null) {
                 TextView tvTitle = (TextView) convertView.findViewById(R.id.tvCourseTitle);
                 tvTitle.setText(course.getTitle());

                 TextView tvDescription = (TextView) convertView.findViewById(R.id.tvCourseDescription);
                 tvDescription.setText(course.getDescription());

                 TextView tvStartTime = (TextView) convertView.findViewById(R.id.tvStartTime);
                 tvStartTime.setText(course.getStartTime());

                 TextView tvEndTime = (TextView) convertView.findViewById(R.id.tvEndTime);
                 tvEndTime.setText(course.getEndTime());

                 TextView tvStartDate = (TextView) convertView.findViewById(R.id.tvStartDate);
                 tvStartDate.setText(course.getStartDate());

                 TextView tvEndDate = (TextView) convertView.findViewById(R.id.tvEndDate);
                 tvEndDate.setText(course.getEndDate());

            }

            return convertView;

        }
    }
}
