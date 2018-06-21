package com.example.yoong.se_expensetracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LogViewAdapter extends RecyclerView.Adapter<LogViewAdapter.ViewHolder> {

    private static final String TAG = "LogViewAdapter";

    private List<Entry> entries;


    public LogViewAdapter(List<Entry> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_layout, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.title.setText(entries.get(position).getCategory());
        holder.amount.setText(Double.toString(entries.get(position).getAmount()));

        /*holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + mTitle.get(position));



            }
        });*/
    }

    @Override
    public int getItemCount() {

        return entries.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView amount;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.logCategory);
            amount = (TextView) itemView.findViewById(R.id.logAmount);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.logLayout);
        }
    }
}
