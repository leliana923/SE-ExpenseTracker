package com.example.yoong.se_expensetracker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class LogViewAdapter extends RecyclerView.Adapter<LogViewAdapter.ViewHolder> {

    private static final String TAG = "LogViewAdapter";

    private List<Entry> entries;
    private Context mContext;


    public LogViewAdapter(List<Entry> entries, Context context) {
        this.entries = entries;
        mContext = context;
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

        holder.symbol.setText(entries.get(position).getSymbol());
        holder.title.setText(entries.get(position).getCategory());
        holder.amount.setText(Double.toString(entries.get(position).getAmount()));
        holder.id.setText(Integer.toString(entries.get(position).getUid()));
        holder.type.setText(entries.get(position).getType());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + entries.get(position));

                Intent intent = new Intent( mContext, EntryDetailsActivity.class);
                intent.putExtra("entry_id", entries.get(position).getUid());
                intent.putExtra( "entry_category", entries.get(position).getCategory());
                intent.putExtra( "entry_type", entries.get(position).getType());
                intent.putExtra( "entry_amount", entries.get(position).getAmount());
                intent.putExtra( "entry_date", entries.get(position).getDate());


                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return entries.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView symbol;
        public TextView title;
        public TextView amount;
        public TextView id;
        public TextView type;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

<<<<<<< HEAD
            symbol = (TextView) itemView.findViewById(R.id.logSymbol);
            title = (TextView) itemView.findViewById(R.id.logCategory);
            amount = (TextView) itemView.findViewById(R.id.logAmount);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.logLayout);
=======
            title = itemView.findViewById(R.id.entry_category);
            amount =  itemView.findViewById(R.id.entry_amount);
            id = itemView.findViewById(R.id.entry_id);
            type = itemView.findViewById(R.id.entry_type);

            parentLayout = itemView.findViewById(R.id.logLayout);
>>>>>>> test
        }
    }
}
