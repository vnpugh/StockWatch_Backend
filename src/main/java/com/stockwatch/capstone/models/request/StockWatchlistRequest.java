package com.stockwatch.capstone.models.request;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class StockWatchlistRequest {

    @NotNull
    private Long watchListId;

    @NotEmpty(message = "Stock symbols cannot be empty")
    private List<String> stockSymbols;

    public StockWatchlistRequest(Long watchListId, List<String> stockSymbols) {
        this.watchListId = watchListId;
        this.stockSymbols = stockSymbols;
    }

    public Long getWatchListId() {
        return watchListId;
    }

    public List<String> getStockSymbols() {
        return stockSymbols;
    }

    @Override
    public String toString() {
        return "AddStockWatchlistRequest{" +
                "watchListId=" + watchListId +
                ", stockSymbols=" + stockSymbols +
                '}';
    }
}
