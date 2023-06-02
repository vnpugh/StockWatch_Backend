package models;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

@Entity
@Table(name="stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Integer stockId;

    @Column
    private String company;

    @Column
    private String symbol;

    @Column
    private Double price;

    @Column
    private Double stockChange;

    @Column
    private Double wallStreetRating;

    @Column
    private BigDecimal marketCap;

    //Multiple stocks can belong to Many watchlists
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "stocks_watchList", joinColumns =
    @JoinColumn(name = "stock_id"), inverseJoinColumns = @JoinColumn(name = "watchList_id"))
    private List<WatchList> watchList;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    public Stock() {
    }

    public Stock(Integer stockId, String company, String symbol, Double price,
                 Double stockChange, Double wallStreetRating, BigDecimal marketCap)
     {
        this.stockId = stockId;
        this.company = company;
        this.symbol = symbol;
        this.price = price;
        this.stockChange = stockChange;
        this.wallStreetRating = wallStreetRating;
        this.marketCap = marketCap;
    }






    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getStockChange() {
        return stockChange;
    }

    public void setStockChange(Double stockChange) {
        this.stockChange = stockChange;
    }

    public Double getWallStreetRating() {
        return wallStreetRating;
    }

    public void setWallStreetRating(Double wallStreetRating) {
        this.wallStreetRating = wallStreetRating;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }


    @Override
    public String toString() {
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        return "Stock{" +
                "stockId=" + stockId +
                ", company='" + company + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", stockChange=" + percentFormat.format(stockChange) +
                ", wallStreetRating=" + wallStreetRating +
                ", marketCap=" + marketCap +
                ", watchList=" + watchList +
                ", industry=" + industry +
                '}';
    }
}
