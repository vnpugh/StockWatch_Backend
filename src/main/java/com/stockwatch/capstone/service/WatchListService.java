package com.stockwatch.capstone.service;

import com.stockwatch.capstone.data.StocksDataLoader;
import com.stockwatch.capstone.exceptions.InvalidInputException;
import com.stockwatch.capstone.exceptions.StockNotFoundException;
import com.stockwatch.capstone.exceptions.WatchListNotFoundException;
import com.stockwatch.capstone.exceptions.WatchlistAlreadyExistsException;
import com.stockwatch.capstone.models.Stock;
import com.stockwatch.capstone.models.User;
import com.stockwatch.capstone.models.WatchList;
import com.stockwatch.capstone.models.request.CreateWatchlistRequest;
import com.stockwatch.capstone.repository.StockRepository;
import com.stockwatch.capstone.repository.UserRepository;
import com.stockwatch.capstone.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    private UserService userService;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setWatchListRepository(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    /**
     * Add stock to watchlist
     *
     * @param symbol
     * @param watchlistId
     * @return Updated watchlist
     */
    public WatchList addStockToWatchlist(String symbol, Long watchlistId) throws InvalidInputException, StockNotFoundException {
        Stock stock = stockService.getStockBySymbol(symbol);
        if (stock == null) {
            throw new StockNotFoundException("No stock exists for given symbol");
        }
        User user = userService.getCurrentLoggedInUser();
        if (user == null) {
            throw new InvalidInputException("User not logged-in");
        }
        WatchList watchList = watchListRepository.findByWatchListIdAndUser(watchlistId, user);

        if (watchList != null) {
            watchList.getStocks().add(stock);
            watchListRepository.save(watchList);
            return watchList;
        } else {
            throw new InvalidInputException("Stock not found for symbol: " + symbol);
        }
    }

    /**
     * Created watchlist
     *
     * @param createWatchlistRequest
     * @return Custom created watchlist
     */
    public WatchList createWatchlist(CreateWatchlistRequest createWatchlistRequest) {
        User user = userService.getCurrentLoggedInUser();
        WatchList watchListExisting = watchListRepository.findByListName(createWatchlistRequest.getListName());
        if (watchListExisting == null) {
            WatchList watchList = new WatchList();
            watchList.setListName(createWatchlistRequest.getListName());
            watchList.setDescription(createWatchlistRequest.getDescription());
            watchList.setDateCreated(LocalDate.now());
            watchList.setUser(user);
            List<Stock> stocks = stockRepository.findByStockIdIn(createWatchlistRequest.getStockIds());
            watchList.setStocks(stocks);
            return watchListRepository.save(watchList);
        } else {
            throw new WatchlistAlreadyExistsException("Watchlist already exists with given name");
        }
    }

    /**
     * Get all stocks by watchlist id
     *
     * @param watchlistId
     * @return List of stocks under watchlist
     */
    public List<Stock> getAllStocksOnWatchList(Long watchlistId) {
        User user = userService.getCurrentLoggedInUser();
        if (user == null) {
            throw new InvalidInputException("User not logged-in");
        }
        WatchList watchlist = watchListRepository.findByWatchListIdAndUser(watchlistId, user);
        return watchlist.getStocks();
    }

    /**
     * Delete stock under watchlist by symbol.
     *
     * @param symbol
     * @param watchlistId
     * @return Updated watchlist
     */
    public WatchList deleteStock(String symbol, Long watchlistId) {//deletes stock from user watchlist
        User user = userService.getCurrentLoggedInUser();
        WatchList watchList = watchListRepository.findByWatchListIdAndUser(watchlistId, user);
        Stock stock = stockService.getStockBySymbol(symbol);
        watchList.getStocks().remove(stock);
        watchListRepository.save(watchList);

        return watchList;
    }

    /**
     * Modify watchlist name.
     *
     * @param watchlistId
     * @param newName
     * @return Modified watchlist.
     */
    public WatchList modifyWatchlist(Long watchlistId, String newName) {
        User user = userService.getCurrentLoggedInUser();
        if (user == null) {
            throw new InvalidInputException("User not logged-in");
        }
        WatchList watchList = watchListRepository.findByWatchListIdAndUser(watchlistId, user);
        if (watchList == null) {
            throw new WatchListNotFoundException("Watchlist not found for given id");
        } else if (newName == null || newName.length() == 0) {
            throw new InvalidInputException("List name cannot be null/empty");
        } else if (newName.equals(watchList.getListName())) {
            throw new InvalidInputException("Given name already matches with existing list");
        }
        watchList.setListName(newName);
        return watchListRepository.save(watchList);
    }
}
