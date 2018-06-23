package com.example.yoong.se_expensetracker;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    //recyclerview
    RecyclerView recyclerView;
    LogViewAdapter adapter;

    //date
    public static String currentDate;

    //amount
    public static Double amount;
    public static DecimalFormat df = new DecimalFormat("0.00");

    //buttons
    Button addincome, addexpense;
    ImageButton settingsBtn, calendarBtn;

    //views
    TextView dateView;
    TextView curView;
    TextView bal;

    //database
    public static AppDatabase db;

    public static String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.expense_log);
        dateView = findViewById(R.id.date_view);
        curView = findViewById(R.id.currencyView);
        bal = findViewById(R.id.balance_view);

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "expense log")
               .fallbackToDestructiveMigration().allowMainThreadQueries().build();

        //List<Entry> entries = db.entryDao().getAllEntries();

        try {
            currency = db.currencyDao().getSelectedCurrency().getCurrency();
        }
        catch (NullPointerException e){
            Currency currency = new Currency("RM");
            db.currencyDao().insertCurrency(currency);
        }
        curView.setText(currency);


        //curView.setText("RM");


        setMainPage();

        addincome = findViewById(R.id.add_income_btn);
        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), IncomeActivity.class));
            }
        });

        addexpense = findViewById(R.id.add_expense_btn);
        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExpenseActivity.class));
            }
        });

        settingsBtn = findViewById((R.id.set_btn));
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        calendarBtn = findViewById(R.id.calendar_btn);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        String fulldate = DateFormat.getDateInstance(DateFormat.FULL).format(new Date());
        dateView.setText(fulldate);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = df.format(c.getTime());
        String fulldate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        setMainPage();

        dateView.setText(fulldate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        curView.setText(currency);
        setMainPage();
    }

    public void balance(){
        List<Entry> entries = db.entryDao().getAllByDates(currentDate);
        amount = 0.00;

        try {
            for (Entry entry : entries) {
                if (entry.getType().equals("INCOME")) {
                    amount += entry.getAmount();
                } else {
                    amount -= entry.getAmount();
                }
            }
        }
        catch (NullPointerException e){
            amount = 0.00;
        }
        bal.setText(df.format(amount).toString());
    }

    private void setMainPage(){
        List<Entry> entries = db.entryDao().getAllByDates(currentDate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LogViewAdapter(entries, this);
        recyclerView.setAdapter(adapter);
        balance();
    }
}