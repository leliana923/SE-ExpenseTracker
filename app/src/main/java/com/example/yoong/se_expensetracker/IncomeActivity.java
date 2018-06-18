package com.example.yoong.se_expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class IncomeActivity extends AppCompatActivity {

    EditText editAmount;
    Button confirmBtn;
    Spinner chooseCat;
    TextView curView;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_layout);

        chooseCat = findViewById(R.id.choose_Category);
        ArrayAdapter<String> mAdapter =
                new ArrayAdapter<String>(IncomeActivity.this, android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.incomeCategory));
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCat.setAdapter(mAdapter);
        chooseCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i == 0) {
                    category = "Income";
                }
                if (i == 1) {
                    category = "Savings";
                }
                if (i == 2) {
                    category = "Allowance";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        minusButton();
        curView = (TextView) findViewById(R.id.currencyView);
        //curView.setText(curview);
        editAmount = (EditText) findViewById(R.id.edit_amount);

    }

    private void minusButton() {

        confirmBtn = findViewById(R.id.cfm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editAmount.getText().toString().trim().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "You did not enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    String amount = editAmount.toString();
                    Entry entry = new Entry();
                    entry.setCategory(category);
                    entry.setDate(currentDate);
                    entry.setSymbol("+");
                    entry.setAmount(Double.parseDouble(amount));
                    db.entryDao().insertAll(entry);
                }
                finish();
            }
        });
    }
}
