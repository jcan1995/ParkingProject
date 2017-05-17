package com.example.bruhshua.parking;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bruhshua.parking.Fragments.ScheduleFragment;
import com.example.bruhshua.parking.Model.Singleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements ScheduleFragment.Callback {

    private TabLayout mTabLayout;
    private ViewPagerAdapter mAdapter;
    private ViewPager mViewPager;

    private static String Time;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
      //  database = FirebaseDatabase.getInstance();

    //    DatabaseReference myRef = database.getReference("Monday");


        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                mAuth.signOut();
                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void queryProbabilities(String time) {
        this.Time = time;
        mViewPager.setCurrentItem(0);
        //Toast.makeText(getApplicationContext(),"Time: " + this.Time,Toast.LENGTH_SHORT).show();
    }

    public static String getTime(){
        return Time;
    }

}
