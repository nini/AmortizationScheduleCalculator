package com.asc;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by nini on 17.09.17.
 */
public class CalculatedValue {
    public final Date date;
    public final BigDecimal debt;
    public final BigDecimal annuitant;
    public final BigDecimal amortization;
    public final BigDecimal burden;

    public CalculatedValue(
            Date date,
            BigDecimal debt,
            BigDecimal annuitant,
            BigDecimal amortization,
            BigDecimal burden
    ) {
        this.date = date;
        this.debt = debt;
        this.annuitant = annuitant;
        this.amortization = amortization;
        this.burden = burden;
    }
}
