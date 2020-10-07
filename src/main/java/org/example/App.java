package org.example;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        FileReaderHelper helper = new FileReaderHelper("config.xml");

        List<PricesPerDate> prices = helper.readEntriesFromFileAsList("pricesPerDate",
                App.class.getClassLoader().getResourceAsStream("MOCK_DATA.csv"));

        List<Double> pricesOnly =  prices.stream().map(p -> p.getPrice())
                .skip(895)
                .collect(Collectors.toList());
        List<Date> datesOnly =  prices.stream()
                .map(p -> p.getTradeDate())
                .skip(895)
                .sorted()
                .collect(Collectors.toList());

        XYChart chart = new XYChartBuilder()
                .xAxisTitle("Dates")
                .yAxisTitle("Prices")
                .build();
//        chart.getStyler().setTheme(new Custom1ChartTheme());
        chart.getStyler().setAxisTickLabelsColor(new Color(225,4,4));
        chart.getStyler().setXAxisTickMarkSpacingHint(60);
        chart.getStyler().setAxisTickPadding(10);

        chart.addSeries("prices", datesOnly, pricesOnly);
        (new SwingWrapper(chart)).displayChart();
    }
}
