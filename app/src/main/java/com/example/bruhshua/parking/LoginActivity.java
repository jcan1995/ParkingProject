package com.example.bruhshua.parking;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.bruhshua.parking.Fragments.LoginFragment;
import com.example.bruhshua.parking.Fragments.RegisterFragment;

/**
 * Created by bruhshua on 5/1/17.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Fragment fragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Framelayout,fragment)
                .commit();
//        getFragmentManager().beginTransaction()
//                .replace(R.id.Framelayout,fragment)
//                .commit();
    }


}
