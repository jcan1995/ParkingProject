package com.example.bruhshua.parking.Fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruhshua.parking.Model.Course;
import com.example.bruhshua.parking.Model.Singleton;
import com.example.bruhshua.parking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bruhshua on 4/24/17.
 */

public class ScheduleFragment extends Fragment {

    private ListView listview;
    private ArrayList<Course> studentCourses;
    private Callback callback;
    private FirebaseDatabase database;
    private  View v;
    private ProgressDialog Dialog;
    public interface Callback{
        public void queryProbabilities(String time);
    }

    public static ScheduleFragment newInstance(ArrayList<Course> courses){

        ScheduleFragment scheduleFragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putSerializable("COURSES", courses);
        scheduleFragment.setArguments(args);
        return scheduleFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Callback){
            callback = (Callback) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_schedule_list_view,container,false);
        Log.d("PArkingMapFragment","3rd Frag");
        studentCourses = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((9 - 0) + 1) + 0;

        String UserNumber = Integer.toString(randomNum);
        Log.d("ScheduleFrag","UserNumber"+UserNumber);
//        DatabaseReference myRef = database.getReference("StudentRecords/0");
        DatabaseReference myRef = database.getReference("StudentRecords/" +UserNumber);

        Dialog = new ProgressDialog(getContext());
        Dialog.setMessage("Fetching data...");
        Dialog.show();

        myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("PArkingMapFragment","Querying data");

                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String title = messageSnapshot.child("Title").getValue().toString();
                        String subject = messageSnapshot.child("Subject").getValue().toString();
                        String desc = messageSnapshot.child("Description").getValue().toString();
                        String loc = messageSnapshot.child("Location").getValue().toString();
                        String room = messageSnapshot.child("Room").getValue().toString();
                        String startT = messageSnapshot.child("StartTime").getValue().toString();
                        String endT = messageSnapshot.child("EndTime").getValue().toString();
                        String startD = messageSnapshot.child("StartDate").getValue().toString();
                        String endD = messageSnapshot.child("EndDate").getValue().toString();
                        Course course5 = new Course(title, subject, "Catalog", "Section", desc, loc, room, startT, endT, startD, endD);

                        studentCourses.add(course5);
                    }

                    //Dialog.hide();
                    Dialog.dismiss();
                    listview = (ListView) v.findViewById(R.id.listView);
                    ScheduleAdapter adapter = new ScheduleAdapter(studentCourses);
                    listview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        return v;
    }


    public class ScheduleAdapter extends ArrayAdapter<Course>{

        public ScheduleAdapter(ArrayList<Course> course) {
            super(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, course);
        }

        @Override
        public int getPosition(Course item) {
            return super.getPosition(item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_schedule_list_view_item,null);

            final Course course = getItem(position);
            if(course != null) {
                 TextView tvTitle = (TextView) convertView.findViewById(R.id.tvCourseTitle);
                 tvTitle.setText(course.getLocation());

                 TextView tvDescription = (TextView) convertView.findViewById(R.id.tvCourseDescription);
                 tvDescription.setText("Room: "+course.getRoom());

                 TextView tvStartTime = (TextView) convertView.findViewById(R.id.tvStartTime);
                 tvStartTime.setText(course.getStartTime());

                 TextView tvEndTime = (TextView) convertView.findViewById(R.id.tvEndTime);
                 tvEndTime.setText(course.getEndTime());

                 TextView tvStartDate = (TextView) convertView.findViewById(R.id.tvStartDate);
                 tvStartDate.setText(course.getStartDate());

                 TextView tvEndDate = (TextView) convertView.findViewById(R.id.tvEndDate);
                 tvEndDate.setText(course.getEndDate());

                Button bGetProbability = (Button) convertView.findViewById(R.id.bQuery);

                bGetProbability.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(getContext(),course.getStartTime(),Toast.LENGTH_SHORT).show();
                        callback.queryProbabilities(course.getStartTime());
                      //  Singleton.setTime(course.getStartTime());


                    }
                });
            }

            return convertView;

        }
    }
}
