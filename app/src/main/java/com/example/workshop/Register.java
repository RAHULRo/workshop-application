package com.example.workshop;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Register extends Fragment {

    Database db;
    TextInputLayout e1,e2,e3;
    TextInputEditText e11;
    Button b1;
    Bundle bundle;
    TextView t1;
    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_register, container, false);
        db = new Database(getActivity());
        e1 = v.findViewById(R.id.e1);
        e2 = v.findViewById(R.id.e2);
        e3 = v.findViewById(R.id.e3);
        b1 = v.findViewById(R.id.b1);
        t1 = v.findViewById(R.id.t1);
        e11 =v.findViewById(R.id.e11);
        bundle = getArguments();
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=  getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Login login = new Login();
                login.setArguments(bundle);
                ft.replace(R.id.layout_main,login);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = e1.getEditText().getText().toString().trim();
                String pass = e2.getEditText().getText().toString().trim();
                String repass = e3.getEditText().getText().toString().trim();
                {if(pass.equals(repass) && user!=null && pass!=null){
                    boolean b = db.checkuser(user);
                    if(b==false) {
                        db.adduser(user, pass);
                        Toast.makeText(getActivity(), "Registered Successfully!!", Toast.LENGTH_SHORT).show();
                        FragmentManager fm=  getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Login login = new Login();
                        login.setArguments(bundle);
                        ft.replace(R.id.layout_main,login);
                        ft.addToBackStack(null);
                        ft.commit();
                    }else {
                        Toast.makeText(getActivity(), "username already exists", Toast.LENGTH_SHORT).show();
                        e11.requestFocus();
                    }
                }
                else{
                    e2.setError(null);
                    e3.setError(null);
                    Toast.makeText(getActivity(), "Password does not match", Toast.LENGTH_SHORT).show();
                }}


            }
        });
        return v;
    }

}
