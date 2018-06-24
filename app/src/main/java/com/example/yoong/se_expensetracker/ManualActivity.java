package com.example.yoong.se_expensetracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class ManualActivity extends AppCompatActivity {

    public static final String FRAGMENT_PDF_RENDERER = "pdf_renderer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_layout);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PdfRendererFragment(),
                            FRAGMENT_PDF_RENDERER)
                    .commit();
        }
    }


}

