package com.example.yoong.se_expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class EntryDetailsActivity extends AppCompatActivity {

    private static final String TAG = "Entry Details Activity";

    EditText edit_amount;
    Button updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrydetails_layout);

        edit_amount = findViewById(R.id.edit_amount);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);

        //edit_amount.setText();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("entry_id") && getIntent().hasExtra("entry_category") &&
                getIntent().hasExtra("entry_amount")){
            Log.d(TAG, "getIncomingIntent: found intent extras");


        }
    }
}
