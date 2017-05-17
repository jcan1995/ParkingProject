package com.example.bruhshua.parking.Fragments;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bruhshua.parking.MainActivity;
import com.example.bruhshua.parking.Model.ParkingLotProbabilityPOJO;
import com.example.bruhshua.parking.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by bruhshua on 4/17/17.
 */

public class ParkingMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private SupportMapFragment mSupportMapFragment;
    private String dayRequested;

    private String time;
    private FirebaseDatabase database;

    private ArrayList<MarkerOptions> markerOptions = new ArrayList<MarkerOptions>();

    private ArrayList<ParkingLotProbabilityPOJO> parkingLotProbabilities;

    public static ParkingMapFragment newInstance(){
        ParkingMapFragment parkingMapFragment = new ParkingMapFragment();
        Bundle args = new Bundle();
        parkingMapFragment.setArguments(args);
        return parkingMapFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map_fragment,container,false);

       // Toast.makeText(getContext(),"Time: "+time,Toast.LENGTH_SHORT).show();
        time = MainActivity.getTime();

        mSupportMapFragment = SupportMapFragment.newInstance();
        FragmentManager fm = getFragmentManager();
        mSupportMapFragment.getMapAsync(this);
        if(!mSupportMapFragment.isAdded())
            fm.beginTransaction().add(R.id.map_fragment,mSupportMapFragment).commit();

        else if(mSupportMapFragment.isAdded())
            fm.beginTransaction().hide(mSupportMapFragment).commit();
        else
            fm.beginTransaction().show(mSupportMapFragment).commit();


        if(time != null){
            Toast.makeText(getContext(),"Time: "+time,Toast.LENGTH_SHORT).show();
            database = FirebaseDatabase.getInstance();

            java.util.Calendar c = java.util.Calendar.getInstance();
            int dayTime = c.get(java.util.Calendar.DAY_OF_WEEK);

            String dayString = getDayOfTheWeekString(dayTime);
            Log.d("ParkingMapFragment","Day of the week string: " + dayString);

            DatabaseReference myRef = database.getReference("Day/"+dayString+"/"+time);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    parkingLotProbabilities = new ArrayList<ParkingLotProbabilityPOJO>();
                    for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

                        String parkingLotName = messageSnapshot.child("ParkingLotName").getValue().toString();
                        Long probability = ((Long) messageSnapshot.child("Probability").getValue());

                        int intProbability = probability.intValue();

                        ParkingLotProbabilityPOJO pojo = new ParkingLotProbabilityPOJO(intProbability,parkingLotName);
                        parkingLotProbabilities.add(pojo);
//                        Log.d("ParkingMapFragment", "ParkingLotName: " + messageSnapshot.child("ParkingLotName").getValue().toString());
//                        Log.d("ParkingMapFragment", "ParkingLotProbability: " + messageSnapshot.child("Probability").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            //Todo: Query database, fill ParkingLotProbabilityPOJOs.
        }

        return v;
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

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        updateUI();

    }

    private void updateUI() {

        if(parkingLotProbabilities != null){

            for(int i = 0; i < parkingLotProbabilities.size();i++){
                MarkerOptions parkingLotMarker = new MarkerOptions();
                parkingLotMarker.position(parkingLotProbabilities.get(i).getLocation());
                parkingLotMarker.title(parkingLotProbabilities.get(i).getParkingLot());
                map.addMarker(parkingLotMarker);
                markerOptions.add(parkingLotMarker);

            }
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for(MarkerOptions marker: markerOptions) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,32);//32
            map.animateCamera(cu);

        }


    }
}
