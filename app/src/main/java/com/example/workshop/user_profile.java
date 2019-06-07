package com.example.workshop;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class user_profile extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button b1,b2;
    ListView l1;
    TextView t1;
    String val;
    public user_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_user_profile, container, false);
        String file  = new AutoLogin().file();
        sharedPreferences = this.getActivity().getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String username = sharedPreferences.getString("user_name",null);
        user_data obj = new user_data(getActivity());
        ArrayList<String> al = obj.retrieve(username);
        b1 = v.findViewById(R.id.tempbtn);
        b2 = v.findViewById(R.id.logout);
        l1 = v.findViewById(R.id.l1);
        t1 = v.findViewById(R.id.heading);



                t1.setText("Hii "+sharedPreferences.getString("user_name",null));


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=  getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.layout_main,new Dashboard());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                FragmentManager fm=  getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Login login  =new Login();
                Bundle b=  new Bundle();
                login.setArguments(b);
                ft.replace(R.id.layout_main,login);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.listview_sections,al);
        l1.setAdapter(adapter);
        return v;
    }

}
