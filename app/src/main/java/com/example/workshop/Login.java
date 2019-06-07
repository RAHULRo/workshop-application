package com.example.workshop;


import android.content.Context;
import android.content.SharedPreferences;
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


public class Login extends Fragment {
    Database db;
    TextInputLayout e1,e2;
    Button b1;
    TextView t1;
    TextInputEditText e11,e12;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String shared_user,userchoice;
    Bundle bmain;
    public Login() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        db = new Database(getActivity());
        String file = new AutoLogin().file();
        sharedPreferences = this.getActivity().getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        e1 = v.findViewById(R.id.e1);
        e2 = v.findViewById(R.id.e2);
        b1 = v.findViewById(R.id.b1);
        t1 = v.findViewById(R.id.t1);
        e11 = v.findViewById(R.id.e11);
        e12 = v.findViewById(R.id.e12);

        Bundle bundle = getArguments();
        userchoice = bundle.getString("userchoice");

        shared_user = sharedPreferences.getString("user_name",null);
        String shared_pass = sharedPreferences.getString("user_pass",null);
        if(shared_pass==null || shared_user==null){
            bmain=bundle;
        }else {
            new user_data(getActivity()).adddata(shared_user,userchoice);
            FragmentManager fm=  getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.layout_main,new user_profile());
            ft.addToBackStack(null);
            ft.commit();
        }

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //fragment change register
                FragmentManager fm=  getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Register register = new Register();
                register.setArguments(bmain);
                ft.replace(R.id.layout_main,register);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = e1.getEditText().getText().toString().trim();
                String pass = e2.getEditText().getText().toString().trim();



                boolean b = db.checkuser(user,pass);
                if(b==true) {
                    editor.putString("user_name",user);
                    editor.putString("user_pass",pass);
                    new username().set(user);
                    editor.commit();
                    Toast.makeText(getActivity(), "Welcome "+user, Toast.LENGTH_SHORT).show();
                    FragmentManager fm=  getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.layout_main,new user_profile());
                    ft.addToBackStack(null);
                    ft.commit();
                }else {
                    Toast.makeText(getActivity(), "Login Failed!!", Toast.LENGTH_SHORT).show();

                    e11.setText("");
                    e12.setText("");
                    e1.requestFocus();

                }
            }
        });
        return v;
    }

}
