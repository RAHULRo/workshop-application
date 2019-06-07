package com.example.workshop;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Dashboard extends Fragment {

    Databaseevents db;
    RecyclerView recyclerView;
    recycler_helper helper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        String file = new AutoLogin().file();
        sharedPreferences = this.getActivity().getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String name = sharedPreferences.getString("user_name",null);



        db = new Databaseevents(getActivity());
        db.addEvents("Dance");
        db.addEvents("FIFA");
        db.addEvents("Painting");
        db.addEvents("Treasure Hunt");
        db.addEvents("Singing");
        db.addEvents("Code club");
        ArrayList<String> al = db.retrieve();

        ArrayList<String> temp= new ArrayList<>();
        for (String val : al)
            temp.add(val);

        recyclerView = v.findViewById(R.id.recycle);
        helper = new recycler_helper(temp);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(helper);

        helper.notifyDataSetChanged();
        db.delete();
        return v;
    }



}
