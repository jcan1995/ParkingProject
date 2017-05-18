package com.example.bruhshua.parking.Fragments;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruhshua.parking.MainActivity;
import com.example.bruhshua.parking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

/**
 * Created by bruhshua on 5/1/17.
 */

public class LoginFragment extends Fragment {
//Test
    private TextView tvRegister;
    private EditText etEmail;
    private EditText etPassword;
    private Button bLogin;
    private ProgressDialog Dialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);

        etEmail = (EditText) v.findViewById(R.id.etEmail);
        etPassword = (EditText) v.findViewById(R.id.etPassword);
        bLogin = (Button) v.findViewById(R.id.bLogin);

        Dialog = new ProgressDialog(getContext());
        Dialog.setMessage("Authenticating...");

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.show();

                if(!etEmail.getText().toString().equals("") || !etPassword.getText().toString().equals("") ) {
                    mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Dialog.dismiss();
                                        Toast.makeText(getActivity().getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                        getActivity().getFragmentManager().popBackStack();
                                        Intent i = new Intent(getActivity(), MainActivity.class);
                                        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(i);
                                    }
                                    if (!task.isSuccessful()) {
                                        Log.d("LoginFragment", "Sign in failed");
                                    }
                                }
                            });
                }

            }
        });

        tvRegister = (TextView) v.findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new RegisterFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Framelayout,fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });



        return v;
    }
}
