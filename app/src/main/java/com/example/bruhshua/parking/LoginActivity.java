package com.example.bruhshua.parking;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.bruhshua.parking.Fragments.LoginFragment;
import com.example.bruhshua.parking.Fragments.RegisterFragment;

/**
 * Created by bruhshua on 5/1/17.
 */

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Fragment fragment = new LoginFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.Framelayout,fragment)
                .commit();
    }


}
