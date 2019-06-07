package com.example.workshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class recycler_helper extends RecyclerView.Adapter<recycler_helper.recylerviewholder> {

    ArrayList<String> al = new ArrayList<>();
    public recycler_helper(ArrayList<String> al1){
        al = al1;
    }

    @NonNull
    @Override
    public recylerviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.item_layout,viewGroup,false);
        return new recylerviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final recylerviewholder recylerviewholder, int i) {
        String val = al.get(i);
        recylerviewholder.t1.setText(val);
        recylerviewholder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                Bundle b =  new Bundle();


                b.putString("userchoice",recylerviewholder.t1.getText().toString());

                FragmentManager fm1=  activity.getSupportFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                Login login = new Login();
                login.setArguments(b);
                ft1.replace(R.id.layout_main,login);
                ft1.addToBackStack(null);
                ft1.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }


    public class recylerviewholder extends RecyclerView.ViewHolder{
        TextView t1;
        Button b1;
        public recylerviewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.item_t1);
            b1 = itemView.findViewById(R.id.item_b1);

        }
    }

}
