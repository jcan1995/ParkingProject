package com.example.bruhshua.parking.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bruhshua.parking.R;

/**
 * Created by bruhshua on 4/19/17.
 */

public class ParkingLotListFragment extends Fragment {



    public static ParkingLotListFragment newInstance(){
        ParkingLotListFragment parkingLotListragment = new ParkingLotListFragment();
        Bundle args = new Bundle();
        parkingLotListragment.setArguments(args);
        return parkingLotListragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_parking_lot_list_view,container,false);


        return v;

    }
}
