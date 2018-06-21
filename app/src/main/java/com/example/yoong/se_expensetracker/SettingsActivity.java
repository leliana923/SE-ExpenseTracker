package com.example.yoong.se_expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.yoong.se_expensetracker.CustomOnItemSelectedListener.currencySymbol;
import static com.example.yoong.se_expensetracker.MainActivity.db;

public class SettingsActivity extends AppCompatActivity {

    private Spinner spinner1;
    private Button submitBtn;
    private Currency c = new Currency(currency);

    public static String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }



    // add items into spinner dynamically

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = findViewById(R.id.currencySpinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = findViewById(R.id.currencySpinner);
        submitBtn = findViewById(R.id.btnSubmit);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.currencyDao().deleteCurrency(c);
                currency = currencySymbol;
                c = new Currency(currency);
                db.currencyDao().insertCurrency(c);


                Toast.makeText(SettingsActivity.this, "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();

                Toast.makeText(SettingsActivity.this,
                        "Currency type " +
                                ": "+ String.valueOf(spinner1.getSelectedItem()),

                        Toast.LENGTH_SHORT).show();
                finish();
            }

        });
    }
}
