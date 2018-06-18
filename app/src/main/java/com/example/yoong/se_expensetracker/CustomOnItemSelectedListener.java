package com.example.yoong.se_expensetracker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Toast;

public class CustomOnItemSelectedListener extends MainActivity implements OnItemSelectedListener {
    public static String currency;

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

        switch(pos){
            case 0: currency = "Lek";
            case 1: currency = "؋";
            case 2: currency = "$";
            case 3: currency = "$";
            case 4: currency = "$";
            case 5: currency = "лв";
            case 6: currency = "R$";
            case 7: currency = "$";
            case 8: currency = "៛";
            case 9: currency = "$";
            case 10: currency = "$";
            case 11: currency = "¥";
            case 12: currency = "$";
            case 13: currency = "₡";
            case 14: currency = "kn";
            case 15: currency = "₱";
            case 16: currency = "Kč";
            case 17: currency = "kr";
            case 18: currency = "£";
            case 19: currency = "$";
            case 20: currency = "₹";
            case 21: currency = "Rp";
            case 22: currency = "¥";
            case 23: currency = "₩";
            case 24: currency = "ден";
            case 25: currency = "RM";
            case 26: currency = "₨";
            case 27: currency = "$";
            case 28: currency = "रु";
            case 29: currency = "ƒ";
            case 30: currency = "$";
            case 31: currency = "₦";
            case 32: currency = "kr";
            case 33: currency = "﷼";
            case 34: currency = "₨";
            case 35: currency = "₱";
            case 36: currency = "﷼";
            case 37: currency = "P";
            case 38: currency = "﷼";
            case 39: currency = "$";
            case 40: currency = "ரூ";
            case 41: currency = "kr";
            case 42: currency = "CHF";
            case 43: currency = "NT$";
            case 44: currency = "฿";
            case 45: currency = "₺";
            case 46: currency = "£";
            case 47: currency = "$";
            case 48: currency = "₫";

        }
        /*Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
