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
import com.stockwatch.capstone.models.request.StockWatchlistRequest;
import com.stockwatch.capstone.models.response.WatchlistResponse;
import com.stockwatch.capstone.repository.StockRepository;
import com.stockwatch.capstone.repository.UserRepository;
import com.stockwatch.capstone.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
     * Adds stocks to a watchlist based on the provided request.
     * @param watchlistRequest The request containing stock symbols and watchlist ID.
     * @return The updated watchlist after adding the stocks.
     * @throws InvalidInputException   If the user is not logged in.
     * @throws StockNotFoundException If no stocks exist for the given symbols.
     */
    public WatchList addStockToWatchlist(StockWatchlistRequest watchlistRequest) throws InvalidInputException, StockNotFoundException {
        List<Stock> stocks = stockRepository.findBySymbolIn(watchlistRequest.getStockSymbols());
        if (stocks == null || stocks.size() == 0) {
            throw new StockNotFoundException("No stock exists for given symbols");
        }
        User user = userService.getCurrentLoggedInUser();
        if (user == null) {
            throw new InvalidInputException("User not logged-in");
        }
        WatchList watchList = watchListRepository.findByWatchListIdAndUser(watchlistRequest.getWatchListId(), user);

        if (watchList != null) {
            watchList.getStocks().addAll(stocks);
            watchListRepository.save(watchList);
            return watchList;
        } else {
            throw new InvalidInputException("Stock not found for symbols: " + watchlistRequest.getStockSymbols());
        }
    }

    /**
     * Creates a new watchlist based on the provided request.
     * @param createWatchlistRequest The request containing watchlist details and associated stock IDs.
     * @return The created watchlist.
     * @throws WatchlistAlreadyExistsException If a watchlist with the same name already exists.
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
     * Retrieves all stocks associated with the specified watchlist.
     * @param watchlistId The ID of the watchlist.
     * @return A list of stocks on the watchlist.
     * @throws InvalidInputException If the user is not logged in.
     * @throws WatchListNotFoundException If the specified watchlist is not found.
     */
    public List<Stock> getAllStocksOnWatchList(Long watchlistId) {
        User user = userService.getCurrentLoggedInUser();
        if (user == null) {
            throw new InvalidInputException("User not logged-in");
        }
        WatchList watchlist = watchListRepository.findByWatchListIdAndUser(watchlistId, user);
        if (watchlist == null) {
            throw new WatchListNotFoundException("Watchlist not found");
        }
        return watchlist.getStocks();
    }

    /**
     * Deletes the specified stocks from the user's watchlist.
     * @param watchlistRequest The request containing the watchlist ID and stock symbols to delete.
     * @return The updated watchlist after removing the stocks.
     * @throws WatchListNotFoundException If the specified watchlist is not found.
     */
    public WatchList deleteStock(StockWatchlistRequest watchlistRequest) {//deletes stock from user watchlist
        User user = userService.getCurrentLoggedInUser();
        WatchList watchList = watchListRepository.findByWatchListIdAndUser(watchlistRequest.getWatchListId(), user);
        List<Stock> stocks = stockRepository.findBySymbolIn(watchlistRequest.getStockSymbols());
        if (watchList != null) {
            watchList.getStocks().removeAll(stocks);
            watchListRepository.save(watchList);
        } else {
            throw new WatchListNotFoundException("Watchlist not found");
        }
        return watchList;
    }

    /**
     * Modifies the name of a watchlist.
     * @param watchlistId The ID of the watchlist to modify.
     * @param newName The new name for the watchlist.
     * @return The updated watchlist after modifying the name.
     * @throws InvalidInputException If the user is not logged in, the new name is null or empty,
     * or the new name already matches the existing list name.
     * @throws WatchListNotFoundException If the specified watchlist is not found.
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

    /**
     * Retrieves all watchlists associated with the currently logged-in user.
     * @return A list of watchlist responses containing the names and IDs of the watchlists.
     * @throws InvalidInputException If the user is not logged in.
     */
    public List<WatchlistResponse> getAllWatchlists() {
        User user = userService.getCurrentLoggedInUser();
        if (user == null) {
            throw new InvalidInputException("User not logged-in");
        }
        List<WatchList> watchLists = watchListRepository.findByUser(user);
        List<WatchlistResponse> watchlistResponses = new ArrayList<>(watchLists.size());
        if (watchLists.size() > 0) {
            watchLists.forEach(watchList -> {
                watchlistResponses.add(new WatchlistResponse(watchList.getListName(), watchList.getWatchListId()));
            });
        }
        return watchlistResponses;
    }
}
