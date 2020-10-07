package org.example.teechart;

import com.steema.teechart.DateTime;
import com.steema.teechart.DateTimeStep;
import com.steema.teechart.TChart;
import com.steema.teechart.axis.AxisLabelAlign;
import com.steema.teechart.axis.AxisLabelItem;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Line;
import com.steema.teechart.themes.*;
import org.example.App;
import org.example.FileReaderHelper;
import org.example.PricesPerDate;

import java.awt.*;
import java.io.IOException;
import java.util.stream.Stream;

public class TCApp {

    public static void main(String[] args) throws IOException {
        FileReaderHelper helper = new FileReaderHelper("config.xml");
        TChart chart1 = new TChart();
        Line lineSeries = new Line(chart1.getChart());
        lineSeries.getMarks().setVisible(false);

        Stream<PricesPerDate> prices = helper.readEntriesFromFile("pricesPerDate",
                App.class.getClassLoader().getResourceAsStream("peru_select_intra_ti_v2.csv"));

        helper.readEntriesFromFile("pricesPerDate",
                App.class.getClassLoader().getResourceAsStream("peru_select_intra_ti_v2.csv"))
                .forEach(System.out::println);

        prices.forEach(p -> {
            lineSeries.add(new DateTime(p.getTradeDate().getTime()), p.getPrice().doubleValue());
        });

//        chart1.getAxes().getBottom().setAutomatic(false);
//        chart1.getAxes().getBottom().setMaximum();

        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(true);
        chart1.getAspect().setTextSmooth(true);
        chart1.setBackground(new Color(255,255,255));
//        chart1.getPanel().setColor(com.steema.teechart.drawing.Color.fromArgb(4,255,255));

        chart1.getLegend().setVisible(false);

        chart1.getAxes().getBottom().setIncrement(Utils.getDateTimeStep(DateTimeStep.THIRTYMINUTES));
//        chart1.getAxes().getBottom().getLabels().setSeparation(75);
        chart1.getAxes().getBottom().getLabels().setDateTimeFormat("HH:mm");
//        chart1.getAxes().getBottom().getLabels().setCustomSize(45);
//        chart1.getAxes().getBottom().getLabels().setAlign(AxisLabelAlign.OPPOSITE);
//        AxisLabelItem item1 = new AxisLabelItem(chart1.getChart());
//        item1.setText("09:00");
//        chart1.getAxes().getBottom().getCustomLabels().add(item1);
//        chart1.getAxes().getBottom().getCustomLabels().add(19);
//        chart1.getAxes().getBottom().setEndPosition(150);
//        System.out.println("bottom axis: "  + chart1.getAxes().getBottom().getCalcIncrement());
        System.out.println("End pos: "  + chart1.getAxes().getBottom().getEndPosition());

        ThemesList.applyTheme(chart1.getChart(), new ReportTheme(chart1.getChart()));

        chart1.getAxes().getBottom().getLabels().getFont().setName("Arial");
        chart1.getAxes().getBottom().getLabels().getFont().setSize(8);

        chart1.getExport().getImage().getPNG().save("/home/marco/images/chart1.png");

    }
}
