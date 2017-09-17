package com.asc;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nini on 14.09.17.
 */
public final class Entrance {
    /**
     * Ctor.
     */
    private Entrance() {

    }

    /**
     * Main entry point.
     *
     * @param args Arguments
     * @throws java.io.IOException If fails
     */
    public static void main(final String... args) throws IOException {
        final DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

        InputReader input = new InputReader(dateFormatter);

        final BigDecimal loan = input.loan;
        final Double borrowingRate = input.borrowingRate;
        final Double amortizationRate = input.amortizationRate;
        final Integer fixedInterestRate = input.fixedInterestRate;
        final Date date = input.date;
        final NumberFormatter numberFormatter = new NumberFormatter();

        MonthlyAnnuitantCalc calc = new MonthlyAnnuitantCalc(
                loan,
                borrowingRate,
                amortizationRate,
                fixedInterestRate,
                date
        );

        CsvPrinter csv = new CsvPrinter(
                numberFormatter,
                dateFormatter
        );
        csv.print(calc.calc());
    }
}
