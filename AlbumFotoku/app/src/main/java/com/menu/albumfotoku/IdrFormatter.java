package com.menu.albumfotoku;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class IdrFormatter {
    public static String format(double num){
        DecimalFormat decFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols idrFormat = new DecimalFormatSymbols();

        idrFormat.setCurrencySymbol("Rp.");
        idrFormat.setMonetaryDecimalSeparator(',');
        idrFormat.setGroupingSeparator('.');

        decFormat.setDecimalFormatSymbols(idrFormat);
        return decFormat.format(num);
    }
}
