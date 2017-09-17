package com.asc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by nini on 17.09.17.
 */
public class MonthlyAnnuitantCalc {
    private BigDecimal debt;
    private final Double borrowingRate;
    private final Double amortizationRate;
    private final Integer fixedInterestRate;
    private Date date;

    /**
     * Ctor.
     */
    public MonthlyAnnuitantCalc(
            final BigDecimal loan,
            final Double borrowingRate,
            final Double amortizationRate,
            final Integer fixedInterestRate,
            final Date date
    ) {
        this.debt = loan;
        this.borrowingRate = borrowingRate;
        this.amortizationRate = amortizationRate;
        this.fixedInterestRate = fixedInterestRate;
        this.date = date;
    }

    public ArrayList<CalculatedValue> calc() {
        BigDecimal accAmortization = new BigDecimal(0);
        BigDecimal accAnnuitant = new BigDecimal(0);

        final BigDecimal burdenRate = new BigDecimal(Double.sum(amortizationRate, borrowingRate), MathContext.DECIMAL32);
        final BigDecimal month = new BigDecimal(12);
        final BigDecimal burden = burdenRate
                .multiply(debt).
                        divide(month, 12, RoundingMode.HALF_EVEN).
                        divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);

        BigDecimal annuitant = debt.
                multiply(new BigDecimal(borrowingRate, MathContext.DECIMAL32)).
                divide(new BigDecimal(12), 2, RoundingMode.HALF_EVEN).
                divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);

        BigDecimal amortization = burden.subtract(annuitant);
        ArrayList<CalculatedValue> result = new ArrayList<>();

        Date date = this.date;

        result.add(new CalculatedValue(
                date,
                debt.negate(),
                new BigDecimal(0),
                debt.negate(),
                debt.negate()
        ));

        Calendar c = Calendar.getInstance();
        System.out.println(month.intValue() * fixedInterestRate);

        for (int m = 1; m <= month.intValue() * fixedInterestRate; m++) {
            debt = debt.subtract(amortization);

            if (debt.compareTo(new BigDecimal(0)) == -1) {
                continue;
            }

            c.setTime(date);
            c.add(Calendar.MONTH, 1);
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            date = c.getTime();

            result.add(new CalculatedValue(
                    date,
                    debt.negate(),
                    annuitant,
                    amortization,
                    burden
            ));

            accAnnuitant = accAnnuitant.add(annuitant);

            annuitant = debt.
                    multiply(new BigDecimal(borrowingRate, MathContext.DECIMAL32)).
                    divide(new BigDecimal(12), 2, RoundingMode.HALF_EVEN).
                    divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);

            accAmortization = accAmortization.add(amortization);

            amortization = burden.subtract(annuitant);
        }

        result.add(new CalculatedValue(
                date,
                debt.negate(),
                accAnnuitant,
                accAmortization,
                burden.multiply(new BigDecimal(month.intValue() * fixedInterestRate))
        ));

        return result;
    }
}