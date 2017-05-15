package com.example.bruhshua.parking;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.bruhshua.parking.Fragments.ParkingLotListFragment;
import com.example.bruhshua.parking.Fragments.ParkingMapFragment;
import com.example.bruhshua.parking.Fragments.ProfileFragment;
import com.example.bruhshua.parking.Model.Singleton;
import com.example.bruhshua.parking.Model.Student;

/**
 * Created by bruhshua on 4/17/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String Fragment[] = {"Map","Other","My Profile"};


    public ViewPagerAdapter(FragmentManager fm) {

        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position){
            case 0:
                ParkingMapFragment parkingMapFragment = ParkingMapFragment.newInstance();
                return parkingMapFragment;

            case 1:
                ParkingLotListFragment parkingLotListFragment = ParkingLotListFragment.newInstance();
                return parkingLotListFragment;

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
