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



public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder> {

    @SuppressLint("StaticFieldLeak")
    private static Context parent;
    private int numberTicket;
    TicketsAdapter(int numberOfItems, Context parent){
        numberTicket = numberOfItems+1;
        TicketsAdapter.parent = parent;
    }

    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        @SuppressLint("ResourceType")
        View view = inflater.inflate(R.layout.number_list_ticket, parent, false);
        return new TicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsViewHolder holder, final int position) {
        holder.bind(position+1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Ticket.class);
                intent.putExtra("numberTicket",  position+1);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return numberTicket;
    }

    static class TicketsViewHolder extends RecyclerView.ViewHolder{
        TextView NumberTicket;
        TextView countGoodAnswers;
        TextView textQuest;
        TextView resultTicket;

        TicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            NumberTicket = itemView.findViewById(R.id.numberTicket);
            countGoodAnswers = itemView.findViewById(R.id.numberGoodAnswers);
            textQuest= itemView.findViewById(R.id.textQuest);
            resultTicket= itemView.findViewById(R.id.reslutTicket);
        }
        @SuppressLint({"SetTextI18n", "ResourceAsColor"})
        void bind(int numberTicket){
            countGoodAnswers.setText(Stats.getResultQuestions(numberTicket-1)+" из 20");
            textQuest.setText("вопросов решено");
            if(Stats.getResultQuestions(numberTicket-1)==20) {
                resultTicket.setText("Билет пройден");
                resultTicket.setTextColor(parent.getResources().getColor(R.color.correctAnswer));
            }
            else if (Stats.getResultQuestions(numberTicket-1)==0){
                resultTicket.setText("Билет не пройден");
                resultTicket.setTextColor(parent.getResources().getColor(R.color.white));
            }
            else{
                resultTicket.setText("Билет не пройден");
                resultTicket.setTextColor(parent.getResources().getColor(R.color.wrongAnswer));
            }

            if(Stats.getResultQuestions(numberTicket-1)==20) {
                NumberTicket.setTextColor(parent.getResources().getColor(R.color.correctAnswer));
                NumberTicket.setText("№ " + numberTicket);
            }
            else if(Stats.getResultQuestions(numberTicket-1)==0){
                NumberTicket.setTextColor(parent.getResources().getColor(R.color.white));
                NumberTicket.setText("№ "+numberTicket);
            }
            else{
                NumberTicket.setTextColor(parent.getResources().getColor(R.color.wrongAnswer));
                NumberTicket.setText("№ "+numberTicket);
            }
        }

    }
}
