package net.springkafkadocker.springkafkadockerint;

import com.opencsv.bean.CsvBindByPosition;

public class Stock {
    @CsvBindByPosition(position = 0)
    private String stockName;
    @CsvBindByPosition(position = 1)
    private String dateTime;
    @CsvBindByPosition(position = 2)
    private String stockValue;

    public String getStockName() {
        return this.stockName;
    }
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getDateTime() {
        return this.dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStockValue() {
        return this.stockValue;
    }
    public void setStockValue(String stockValue) {
        this.stockValue = stockValue;
    }
}
