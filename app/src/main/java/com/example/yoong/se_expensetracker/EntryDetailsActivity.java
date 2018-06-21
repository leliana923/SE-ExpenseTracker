package com.example.yoong.se_expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.yoong.se_expensetracker.MainActivity.db;

public class EntryDetailsActivity extends AppCompatActivity {

    private static final String TAG = "Entry Details Activity";

    EditText edit_amount;
    Spinner chooseCat;
    TextView curView;

    String category;
    String entry_type;
    int entry_id;
    String entry_date;
    ArrayAdapter<String> mAdapter;

    //buttons
    Button updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrydetails_layout);

        chooseCat = findViewById(R.id.choose_Category);
        edit_amount = findViewById(R.id.edit_amount);
        curView = findViewById(R.id.currencyView);

        getIncomingIntent();

        if(entry_type.equals("INCOME")){
            mAdapter = new ArrayAdapter<>(EntryDetailsActivity.this, android.R.layout.simple_list_item_1,
                            getResources().getStringArray(R.array.incomeCategory));
        }else if(entry_type.equals("EXPENSE")){
            mAdapter = new ArrayAdapter<>(EntryDetailsActivity.this, android.R.layout.simple_list_item_1,
                            getResources().getStringArray(R.array.expenseCategory));
        }
        chooseCat.setAdapter(mAdapter);
        chooseCat.setSelection(mAdapter.getPosition(category));
        chooseCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                category = mAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Entry entry = new Entry();
                entry.setUid(entry_id);
                entry.setType(entry_type);
                entry.setDate(entry_date);
                entry.setCategory(category);
                entry.setAmount(Double.parseDouble(edit_amount.getText().toString()));

                db.entryDao().updateEntry(entry);

                Toast.makeText(getApplicationContext(),"Entry updated", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        deleteBtn = findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Entry entry = new Entry();
                entry.setUid(entry_id);

                db.entryDao().deleteEntry(entry);

                Toast.makeText(getApplicationContext(),"Entry deleted", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("entry_id") && getIntent().hasExtra("entry_category") &&
                getIntent().hasExtra("entry_amount") && getIntent().hasExtra("entry_type")
                && getIntent().hasExtra("entry_date")){
            Log.d(TAG, "getIncomingIntent: found intent extras");

            int entryID = getIntent().getIntExtra("entry_id", 0);
            String entryCategory = getIntent().getStringExtra("entry_category");
            double entryAmount = getIntent().getDoubleExtra("entry_amount", 0.0);
            String entryType = getIntent().getStringExtra("entry_type");
            String entryDate = getIntent().getStringExtra("entry_date");

            setEntryDetails( entryID, entryCategory, entryAmount, entryType, entryDate);
        }


    }

    private void setEntryDetails(int entryID, String entryCategory, double entryAmount, String entryType, String entryDate){
        entry_id = entryID;
        category = entryCategory;
        edit_amount.setText(Double.toString(entryAmount));
        entry_type = entryType;
        entry_date = entryDate;

    }
}
