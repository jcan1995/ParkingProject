package com.example.bruhshua.parking.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruhshua.parking.Model.Course;
import com.example.bruhshua.parking.Model.ParkingLotProbabilityPOJO;
import com.example.bruhshua.parking.R;
import com.github.lzyzsd.circleprogress.CircleProgress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruhshua on 4/19/17.
 */

public class ParkingLotListFragment extends Fragment {

    private ListView listView;
    private ArrayList<ParkingLotProbabilityPOJO> pojo;

    public static ParkingLotListFragment newInstance(ArrayList<ParkingLotProbabilityPOJO> pojo){
        ParkingLotListFragment parkingLotListragment = new ParkingLotListFragment();
        Bundle args = new Bundle();
        args.putSerializable("POJO",pojo);
        parkingLotListragment.setArguments(args);
        return parkingLotListragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_parking_lot_list_view,container,false);
        pojo = (ArrayList<ParkingLotProbabilityPOJO>) getArguments().getSerializable("POJO");

        if(pojo != null){
            ParkingLotAdapter adapter = new ParkingLotAdapter(pojo);
            listView = (ListView) v.findViewById(R.id.parkingLotListView);
            listView.setAdapter(adapter);
        }



        return v;

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
                tvParkingLotName.setText(pojo.getParkingLot());

                CircleProgress probability = (CircleProgress) convertView.findViewById(R.id.progressProbability);
                probability.setProgress(pojo.getProbability());

            }

            return convertView;

        }
    }

}
