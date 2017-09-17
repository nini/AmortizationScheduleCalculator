package com.asc;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nini on 17.09.17.
 */
public class CsvPrinter {
    private final NumberFormatter numberFormatter;
    private final DateFormat dateFormatter;

    public CsvPrinter(
            NumberFormatter numberFormatter,
            DateFormat dateFormatter
    ) {
        this.numberFormatter = numberFormatter;
        this.dateFormatter = dateFormatter;
    }

    public void print(ArrayList<CalculatedValue> list) throws IOException {
        final List<String> headerRow = new ArrayList<>();
        headerRow.add("Datum" + '\t');
        headerRow.add("Restschuld");
        headerRow.add("Zinsen");
        headerRow.add("Tilgung (+) / Auszahlung (-)");
        headerRow.add("Rate");

        CSVFormat csvFileFormat = CSVFormat.RFC4180.withHeader().withDelimiter('\t').withQuote(null);

        CSVPrinter printer = new CSVPrinter(
                System.out,
                csvFileFormat
        );

        printer.printRecord(headerRow);

        Iterator<CalculatedValue> it = list.iterator();
        while (it.hasNext()) {
            CalculatedValue value = it.next();
            if (it.hasNext()) {
                printer.printRecord(new String[]{
                        dateFormatter.format(value.date),
                        numberFormatter.format(value.debt),
                        numberFormatter.format(value.annuitant),
                        numberFormatter.format(value.amortization),
                        numberFormatter.format(value.burden),
                });
            } else {
                printer.printRecord(new String[]{
                        "Zinsbindungsende",
                        numberFormatter.format(value.debt),
                        numberFormatter.format(value.annuitant),
                        numberFormatter.format(value.amortization),
                        numberFormatter.format(value.burden),
                });
            }
        }

        printer.close();
    }
}
