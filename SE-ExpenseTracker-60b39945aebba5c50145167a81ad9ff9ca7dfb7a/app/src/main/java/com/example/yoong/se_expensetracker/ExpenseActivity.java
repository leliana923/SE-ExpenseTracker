package com.example.yoong.se_expensetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.yoong.se_expensetracker.MainActivity.currentDate;
import static com.example.yoong.se_expensetracker.MainActivity.db;
import static com.example.yoong.se_expensetracker.SettingsActivity.currency;

public class ExpenseActivity extends AppCompatActivity {

    EditText editAmount;
    Button confirmBtn;
    Spinner chooseCat;
    TextView curView;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_layout);

        chooseCat = findViewById(R.id.choose_Category);
        ArrayAdapter<String> myAdapter =
                new ArrayAdapter<String>(ExpenseActivity.this, android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.expenseCategory));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCat.setAdapter(myAdapter);
        chooseCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 0) {
                    category = "Eating Out";
                }
                if (i == 1) {
                    category = "Clothes";
                }
                if (i == 2) {
                    category = "Food";
                }
                if (i == 3) {
                    category = "Bills";
                }
                if (i == 4) {
                    category = "Entertainment";
                }
                if (i == 5) {
                    category = "Others";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        minusButton();
        curView = (TextView) findViewById(R.id.currencyView);
        curView.setText(currency);
        editAmount = (EditText) findViewById(R.id.edit_amount);

    }

    private void minusButton() {

        confirmBtn = findViewById(R.id.cfm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editAmount.getText().toString().trim().isEmpty()){
                    Toast.makeText(ExpenseActivity.this, "You did not enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Entry entry = new Entry();
                    entry.setCategory(category);
                    entry.setDate(currentDate);
                    entry.setSymbol("-");
                    entry.setAmount(Double.parseDouble(editAmount.getText().toString()));
                    db.entryDao().insertAll(entry);

                }
                finish();
            }
        });
    }
}

