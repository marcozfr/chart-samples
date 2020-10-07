package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PricesPerDate implements Comparable<PricesPerDate>{

    private String nro;
    private Double price;
    private Date tradeDate;

    @Override
    public int compareTo(PricesPerDate o) {
        return this.getTradeDate().compareTo(o.getTradeDate());
    }
}
