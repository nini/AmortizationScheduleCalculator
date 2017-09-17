package com.asc;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by nini on 17.09.17.
 */
public class NumberFormatter {
    private final DecimalFormat decimalFormat;

    NumberFormatter() {
        this(new Locale("de", "DE"));
    }

    NumberFormatter(Locale locale) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);

        String pattern = "###,##0.00## ¤";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);

        this.decimalFormat = decimalFormat;
    }

    public String format(BigDecimal input) {
        return decimalFormat.format(input);
    }
}
