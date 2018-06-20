package com.example.yoong.se_expensetracker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Toast;

public class CustomOnItemSelectedListener extends MainActivity implements OnItemSelectedListener {
    public static String currencySymbol;

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

        switch(pos){
            case 0:
                currencySymbol = "Lek";
                break;
            case 1:
                currencySymbol = "؋";
                break;
            case 2:
                currencySymbol = "$";
                break;
            case 3:
                currencySymbol = "$";
                break;
            case 4:
                currencySymbol = "$";
                break;
            case 5:
                currencySymbol = "лв";
                break;
            case 6:
                currencySymbol = "R$";
                break;
            case 7:
                currencySymbol = "$";
                break;
            case 8:
                currencySymbol = "៛";
                break;
            case 9:
                currencySymbol = "$";
                break;
            case 10:
                currencySymbol = "$";
                break;
            case 11:
                currencySymbol = "¥";
                break;
            case 12:
                currencySymbol = "$";
                break;
            case 13:
                currencySymbol = "₡";
                break;
            case 14:
                currencySymbol = "kn";
                break;
            case 15:
                currencySymbol = "₱";
                break;
            case 16:
                currencySymbol = "Kč";
                break;
            case 17:
                currencySymbol = "kr";
                break;
            case 18:
                currencySymbol = "£";
                break;
            case 19:
                currencySymbol = "$";
                break;
            case 20:
                currencySymbol = "₹";
                break;
            case 21:
                currencySymbol = "Rp";
                break;
            case 22:
                currencySymbol = "¥";
                break;
            case 23:
                currencySymbol = "₩";
                break;
            case 24:
                currencySymbol = "ден";
                break;
            case 25:
                currencySymbol = "RM";
                break;
            case 26:
                currencySymbol = "₨";
                break;
            case 27:
                currencySymbol = "$";
                break;
            case 28:
                currencySymbol = "रु";
                break;
            case 29:
                currencySymbol = "ƒ";
                break;
            case 30:
                currencySymbol = "$";
                break;
            case 31:
                currencySymbol = "₦";
                break;
            case 32:
                currencySymbol = "kr";
                break;
            case 33:
                currencySymbol = "﷼";
                break;
            case 34:
                currencySymbol = "₨";
                break;
            case 35:
                currencySymbol = "₱";
                break;
            case 36:
                currencySymbol = "﷼";
                break;
            case 37:
                currencySymbol = "P";
                break;
            case 38:
                currencySymbol = "﷼";
                break;
            case 39:
                currencySymbol = "$";
                break;
            case 40:
                currencySymbol = "ரூ";
                break;
            case 41:
                currencySymbol = "kr";
                break;
            case 42:
                currencySymbol = "CHF";
                break;
            case 43:
                currencySymbol = "NT$";
                break;
            case 44:
                currencySymbol = "฿";
                break;
            case 45:
                currencySymbol = "₺";
                break;
            case 46:
                currencySymbol = "£";
                break;
            case 47:
                currencySymbol = "$";
                break;
            case 48:
                currencySymbol = "₫";
                break;

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
