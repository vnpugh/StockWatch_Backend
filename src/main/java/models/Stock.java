package models;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column
    private String company;

    @Column
    private String symbol;

    @Column
    private Double price;

    @Column
    private Double stockChange;

    @Column
    private Double stockGain;

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

    public Stock(String company, String symbol,
                 Double price, Double stockChange, Double stockGain) {

        this.company = company;
        this.symbol = symbol;
        this.price = price;
        this.stockChange = stockChange;
        this.stockGain = stockGain;
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

    public Double getStockGain() {
        return stockGain;
    }

    public void setStockGain(Double stockGain) {
        this.stockGain = stockGain;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", company='" + company + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", stockChange=" + stockChange +
                ", stockGain=" + stockGain +
                ", watchList=" + watchList +
                '}';
    }
}
