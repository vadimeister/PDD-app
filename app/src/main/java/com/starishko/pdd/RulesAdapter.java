package com.starishko.pdd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class RulesAdapter extends RecyclerView.Adapter<RulesAdapter.RulesViewHolder> {

    @SuppressLint("StaticFieldLeak")
    private static Context parent;
    private int numberRules;
    RulesAdapter(int numberOfItems, Context parent){

        numberRules = numberOfItems;
        RulesAdapter.parent = parent;
    }

    @NonNull
    @Override
    public RulesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        @SuppressLint("ResourceType")
        View view = inflater.inflate(R.layout.number_list_rule, parent, false);


        return new RulesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RulesViewHolder holder, final int position) {
        holder.bind(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Rule.class);
                intent.putExtra("numberTheme",  position+1);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return numberRules;
    }

    static class RulesViewHolder extends RecyclerView.ViewHolder{
        TextView NumberThemeRule;

        RulesViewHolder(@NonNull View itemView) {
            super(itemView);
            NumberThemeRule = itemView.findViewById(R.id.number_list_rule);

        }

        void bind(int numberTheme){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference myRefNumberTheme = database.child ("Rules").child (String.valueOf(numberTheme+1));

            myRefNumberTheme .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.child("Theme").getValue(String.class);
                    NumberThemeRule.setText(value);
                }
                @Override
                public void onCancelled(@NotNull DatabaseError databaseError) {

                }
            });


        }

    }
}
