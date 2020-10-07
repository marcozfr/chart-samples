package org.example;

import org.knowm.xchart.style.AbstractBaseTheme;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.ChartColor;

import java.awt.*;

public class Custom1ChartTheme extends AbstractBaseTheme {

    private static final Font BASE_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 4);

    @Override
    public Color getChartBackgroundColor() {
        return ChartColor.getAWTColor(ChartColor.WHITE);
    }

    @Override
    public int getAxisTickPadding() {
        return 1;
    }

    @Override
    public Color getAxisTickMarksColor() {
        return new Color(255, 27, 27);
    }

    @Override
    public int getAxisTickMarkLength() {
        return 2;
    }

    @Override
    public int getXAxisTickMarkSpacingHint() {
        return 1;
    }
}
