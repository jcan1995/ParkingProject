package com.example.bruhshua.parking;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.bruhshua.parking.Fragments.ParkingLotListFragment;
import com.example.bruhshua.parking.Fragments.ParkingMapFragment;
import com.example.bruhshua.parking.Fragments.ProfileFragment;
import com.example.bruhshua.parking.Fragments.ScheduleFragment;
import com.example.bruhshua.parking.Model.Singleton;
import com.example.bruhshua.parking.Model.Student;

/**
 * Created by bruhshua on 4/17/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String Fragment[] = {"Map","List","Profile","My Schedule"};
    private Context context;//Student data can be passed to any fragment from this point.

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getItemPosition(Object object) {

        Log.d("ViewPagerJosh","parkinglotlistfrag");

        return super.getItemPosition(object);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position){
            case 0:
                ParkingMapFragment parkingMapFragment = ParkingMapFragment.newInstance();
                return parkingMapFragment;

            case 1:
                ParkingLotListFragment parkingLotListFragment = ParkingLotListFragment.newInstance(Singleton.getInstance(context).getProbabilities());
                return parkingLotListFragment;

            case 3:
                ScheduleFragment scheduleFragment = ScheduleFragment.newInstance(Singleton.getInstance(context).getStudent().getCourses());
                return scheduleFragment;

            case 2:
                ProfileFragment profileFragment = ProfileFragment.newInstance();
                return profileFragment;
            default:
                return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Fragment[position];
    }

    @Override
    public int getCount() {
        return Fragment.length;
    }
}
