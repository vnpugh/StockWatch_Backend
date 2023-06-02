package service;

import exceptions.StockNotFoundException;
import io.cucumber.java.eo.Se;
import models.Stock;
import models.User;
import models.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import repository.WatchListRepository;

import java.util.List;

@Service
public class WatchListService {
    private UserRepository userRepository;
    private WatchListRepository watchListRepository;
    private Object stockService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setWatchListRepository(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }



    public Stock addStockToWatchlist(User user, String symbol) {
        Stock stock = stockService.getStockBySymbol(symbol);

        if (stock != null) {
            user.getWatchList().addStock(stock);
            return stock;
        } else {
            throw new StockNotFoundException("Stock not found for symbol: " + symbol);
        }
    }



    public List<Stock> getAllStocksOnWatchList(User user) {
    }

    public void deleteStock(User user, String symbol) {
    }
}
