package com.example.bruhshua.parking.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruhshua.parking.MainActivity;
import com.example.bruhshua.parking.Model.Course;
import com.example.bruhshua.parking.Model.ParkingLotProbabilityPOJO;
import com.example.bruhshua.parking.Model.Singleton;
import com.example.bruhshua.parking.R;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruhshua on 4/19/17.
 */

public class ParkingLotListFragment extends Fragment{

    private ListView listView;
    private ArrayList<ParkingLotProbabilityPOJO> parkingLotProbabilities;
    private FirebaseDatabase database;
    private String time;

    private View v;


    public static ParkingLotListFragment newInstance(ArrayList<ParkingLotProbabilityPOJO> pojo){
        ParkingLotListFragment parkingLotListragment = new ParkingLotListFragment();
        Bundle args = new Bundle();
        args.putSerializable("POJO",pojo);
        parkingLotListragment.setArguments(args);
        return parkingLotListragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_parking_lot_list_view,container,false);
        time = MainActivity.getTime();

        if(parkingLotProbabilities != null){
            Log.d("ParkingLotListFrag","inside if");

            ParkingLotAdapter adapter = new ParkingLotAdapter(parkingLotProbabilities);
            listView = (ListView) v.findViewById(R.id.parkingLotListView);
            listView.setAdapter(adapter);
        }

        if(time != null){
            database = FirebaseDatabase.getInstance();

            java.util.Calendar c = java.util.Calendar.getInstance();

            int dayTime = c.get(java.util.Calendar.DAY_OF_WEEK);
            String dayString = getDayOfTheWeekString(dayTime);

            DatabaseReference myRef = database.getReference("Day/"+dayString+"/"+time);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("ParkingLotListFrag","querying data");

                    parkingLotProbabilities = new ArrayList<ParkingLotProbabilityPOJO>();
                    for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

                        String parkingLotName = messageSnapshot.child("ParkingLotName").getValue().toString();
                        Long probability = ((Long) messageSnapshot.child("Probability").getValue());

                        int intProbability = probability.intValue();

                        ParkingLotProbabilityPOJO pojo = new ParkingLotProbabilityPOJO(intProbability,parkingLotName);
                        parkingLotProbabilities.add(pojo);

                    }
                    updateUI();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            Log.d("ParkingLotListFrag","time is null");

        }

        return v;
    }

    private void updateUI() {
        Log.d("ParkingLotListFrag","inside update UI");

        ParkingLotAdapter adapter = new ParkingLotAdapter(parkingLotProbabilities);
        listView = (ListView) v.findViewById(R.id.parkingLotListView);
        listView.setAdapter(adapter);

    }

    private String getDayOfTheWeekString(int day) {
        switch (day){
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "Monday";
        }
    }
    public class ParkingLotAdapter extends ArrayAdapter<ParkingLotProbabilityPOJO> {

        public ParkingLotAdapter(ArrayList<ParkingLotProbabilityPOJO> pojo) {
            super(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, pojo);
        }

        @Override
        public int getPosition(ParkingLotProbabilityPOJO item) {
            return super.getPosition(item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_parking_lot_list_view_item,null);//Todo: updatelayout to listview item.

            ParkingLotProbabilityPOJO pojo = getItem(position);

            if(pojo != null) {

                TextView tvParkingLotName = (TextView) convertView.findViewById(R.id.tvParkingLotName);
                tvParkingLotName.setText("Parking Lot "+pojo.getParkingLot());

                CircleProgress probability = (CircleProgress) convertView.findViewById(R.id.progressProbability);
                probability.setProgress(pojo.getProbability());

            }

            return convertView;

        }
    }

}
