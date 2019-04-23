package com.nasir.medicaladviser.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.nasir.medicaladviser.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment{
    EditText username, password;
    AppCompatButton btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        // Inflate the layout for this fragment
        btn=rootView.findViewById(R.id.loginbtn);
        username=rootView.findViewById(R.id.username);
        password=rootView.findViewById(R.id.password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("12345")) {

                }else{
                    Toast.makeText(getContext(), "Username and password is error", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }
}
