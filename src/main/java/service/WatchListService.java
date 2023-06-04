package service;

import data.StocksDataLoader;
import exceptions.StockNotFoundException;
import models.Stock;
import models.User;
import models.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.StockRepository;
import repository.UserRepository;
import repository.WatchListRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatchListService {
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private StocksDataLoader stocksDataLoader;
    private User user;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setWatchListRepository(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }



    public Stock addStockToWatchlist(String symbol) {
        Stock stock = stockService.getStockBySymbol(symbol);

        if (stock != null) {
            List<WatchList> watchlist = user.getWatchList();
            watchlist.add(stock);
            return stock;
        } else {
            throw new StockNotFoundException("Stock not found for symbol: " + symbol);
        }   }


    public List<Stock> getAllStocksOnWatchList(User user) {
        List<WatchList> watchlist = user.getWatchList();
        List<Stock> stocks = new ArrayList<>();

        for (WatchList watchList : watchlist) {
            stocks.addAll(watchList.getStocks());
        }

        return stocks;
    }



    public void deleteStock(User user, String symbol) {//deletes stock from user watchlist
        List<WatchList> watchList = user.getWatchList();
        watchList.removeIf(stock -> stock instanceof Stock && ((Stock) stock).getSymbol().equals(symbol));
    }





}
