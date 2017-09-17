package com.asc;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by nini on 17.09.17.
 */
public class InputReader {
    //Should be final
    public Date date;
    public BigDecimal loan;
    public Double borrowingRate;
    public Double amortizationRate;
    public Integer fixedInterestRate;

    public InputReader(DateFormat dateFormatter) {
        java.util.Scanner input = new Scanner(System.in);

        System.out.println("Please input date, e.g. 30.11.2015");
        try {
            this.date = dateFormatter.parse(input.nextLine());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(13);
        }

        System.out.println("Please loan amount, e.g. 100000");
        try {
            this.loan = new BigDecimal(input.nextLine().replaceAll(",", ""));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(13);
        }

        System.out.println("Please borrowing rate, e.g. 2.12");
        try {
            this.borrowingRate = new Double(input.nextLine().replaceAll(",", "."));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(13);
        }

        System.out.println("Please starting amortization rate, e.g. 2");
        try {
            this.amortizationRate = new Double(input.nextLine().replaceAll(",", "."));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(13);
        }

        System.out.println("Please fixed interest rate, in years, e.g. 10");
        try {
            this.fixedInterestRate = Integer.valueOf(input.nextLine()).intValue();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(13);
        }
    }
}
