package com.stockwatch.capstone.service;

import com.stockwatch.capstone.exceptions.InvalidInputException;
import com.stockwatch.capstone.exceptions.WatchListNotFoundException;
import com.stockwatch.capstone.models.Stock;
import com.stockwatch.capstone.models.User;
import com.stockwatch.capstone.models.WatchList;
import com.stockwatch.capstone.repository.StockRepository;
import com.stockwatch.capstone.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private StockRepository stockRepository;

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private UserService userService;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Filter stock by symbol
     * @param symbol
     * @return Stock that matches symbol.
     */
    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbolIgnoreCase(symbol);
    }

    /**
     * Search stocks by symbol or company
     *
     * @param query
     * @return List of stocks matching the query
     */
    public List<Stock> searchStockByCompanyOrSymbol(String query, Long watchlistId) {
        if ((query == null || query.length() == 0) && watchlistId == null) {
            return getAllStocks();
        } else if (watchlistId == null) {
            return stockRepository.findByCompanyEqualsIgnoreCaseOrSymbolEqualsIgnoreCase(query, query);
        } else {
            User user = userService.getCurrentLoggedInUser();
            if (user == null) {
                throw new InvalidInputException("User not logged-in");
            }
            WatchList watchlist = watchListRepository.findByWatchListIdAndUser(watchlistId, user);
            if (watchlist == null) {
                throw new WatchListNotFoundException("Watchlist not found");
            }
            return watchlist.getStocks().stream().filter(stock -> {
                if (query == null || query.length() == 0) {
                    return true;
                } else {
                    return stock.getSymbol().equalsIgnoreCase(query) || stock.getCompany().equalsIgnoreCase(query);
                }
            }).toList();
        }
    }

    /**
     * Get list af all stocks in the system.
     *
     * @return
     */
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }


}
