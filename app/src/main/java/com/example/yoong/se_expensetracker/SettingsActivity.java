package com.example.yoong.se_expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.yoong.se_expensetracker.CustomOnItemSelectedListener.currencySymbol;
import static com.example.yoong.se_expensetracker.MainActivity.currency;
import static com.example.yoong.se_expensetracker.MainActivity.db;

public class SettingsActivity extends AppCompatActivity {

    private Spinner selectCurrency;
    private Button submitBtn;
    ArrayAdapter<String> mAdapter;
    private static String currentCurrency;
    private Currency c = new Currency(currency);


    //public static String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        if(currentCurrency==null){
            currentCurrency = "Malaysia Ringgit";
        }

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }



    // add items into spinner dynamically

    public void addListenerOnSpinnerItemSelection() {
        selectCurrency = findViewById(R.id.currencySpinner);
        mAdapter = new ArrayAdapter<String>(SettingsActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.currency_arrays));
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCurrency.setAdapter(mAdapter);
        selectCurrency.setSelection(mAdapter.getPosition(currentCurrency));
        selectCurrency.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        selectCurrency = findViewById(R.id.currencySpinner);
        submitBtn = findViewById(R.id.btnSubmit);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                db.currencyDao().deleteCurrency(c);
                currency = currencySymbol;
                currentCurrency = selectCurrency.getSelectedItem().toString();
                c = new Currency(currency);
                db.currencyDao().insertCurrency(c);

                Toast.makeText(SettingsActivity.this,
                        "Currency type " +
                                ": "+ String.valueOf(selectCurrency.getSelectedItem()),

                        Toast.LENGTH_SHORT).show();
                finish();
            }

        });
    }
}
