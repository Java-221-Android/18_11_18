package com.sheygam.java_221_18_11_18_cw;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<User> list;
    private RowClickListener listener;

    public MyAdapter() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new User("User " + (i+1),"user" + (i+1) + "@mail.com"));
        }
    }

    public void setListener(RowClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MY_TAG", "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("MY_TAG", "onBindViewHolder: " + position);
        User user = list.get(position);
        holder.titleTxt.setText(user.getTitle());
        holder.emailTxt.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add() {
        list.add(3,new User("Fake","fake@mail.com"));
        notifyItemInserted(3);
    }

    public void remove(){
        list.remove(2);
        notifyItemRemoved(2);
    }

    public void remove(int adapterPosition) {
        list.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    public void move(int from, int to){
        User u = list.remove(from);
        list.add(to,u);
        notifyItemMoved(from,to);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTxt, emailTxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.title_txt);
            emailTxt = itemView.findViewById(R.id.email_txt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onRowClick(v,getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listener != null){
                        listener.onRowLongClick(v,getAdapterPosition());
                    }
                    return true;
                }
            });


        }
    }

    public interface RowClickListener{
        void onRowClick(View view, int pos);
        void onRowLongClick(View view, int pos);
    }
}
