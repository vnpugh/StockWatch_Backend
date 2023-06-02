package controller;

import models.Stock;
import models.User;
import models.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import service.WatchListService;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class WatchListController {
    private WatchListService watchListService;
    private UserRepository userRepository;

    @Autowired
    public void setWatchListService(WatchListService watchListService){
        this.watchListService = watchListService;
    }


    /**
     * GET: endpoint http://localhost:8080/api/watchlist
     * Retrieves (view) all stocks on the user's watchlist.
     * @return a list of stocks on the watchlist
     */
    @GetMapping(path = "/watchlist")
    public List<Stock> getAllStocksOnWatchList(){
        User user = getLoggedInUser();
        return watchListService.getAllStocksOnWatchList(user);
    }



    /**
     *  POST: endpoint http://localhost:8080/api/watchlist
     *  Create a new watchlist for the logged-in user.
     *  The new watchlist is saved for the user (User Repository)
     * @return ResponseEntity<WatchList> The created watchlist with HTTP status 201 (Created).
     */
    @PostMapping(path = "/watchlist")
    public ResponseEntity<WatchList> createWatchList() {
        User user = getLoggedInUser();
        WatchList watchList = new WatchList();
        user.addWatchList(watchList);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(watchList);
    }



    /**
     * POST: endpoint http://localhost:8080/api/watchlist/stocks/{symbol}
     * Adds a stock to the user's watchlist by the specified symbol.
     * @param symbol the ticker symbol of the stock to add
     * @return the updated watchlist of the user
     */
    @PostMapping(path = "/watchlist/stocks/{symbol}")
    public List<WatchList> addStockToWatchList(@PathVariable String symbol) {
        User user = getLoggedInUser();
        Stock stock = watchListService.addStockToWatchlist(symbol);
        return user.getWatchList();
    }



    private User getLoggedInUser() { // method to get the logged-in user (dummy user)
        User user = new User();
        user.setEmail("email100@gmail.com");
        return user;
    }


    /**
     * PUT: endpoint http://localhost:8080/api/watchlist/{watchListId}
     * Update the name of a watchlist for the logged-in user.
     * @param watchListId The ID of the watchlist to update.
     * @param newListName     The new name for the watchlist.
     * @return ResponseEntity<WatchList> The updated watchlist with HTTP status 200 (OK).
     *         If the watchlist is not found, returns HTTP status 404 (Not Found).
     */
    @PutMapping(path = "/watchlist/{watchListId}")
        public ResponseEntity<WatchList> updateWatchListName(@PathVariable Long watchListId, Long newListName) {
            User user = getLoggedInUser();
            WatchList watchList = user.findWatchListByWatchListId(watchListId);

            if (watchList != null) {
                watchList.setListName();updateWatchListName(newListName, newListName);
                userRepository.save(user);   // Save the user to the updated watchlist
                return ResponseEntity.ok(watchList);
            } else {
                return ResponseEntity.notFound().build();
            }
    }




    /**
     * Delete a stock from the user's watchlist by symbol.
     * @param symbol The symbol of the stock to delete.
     * @return List<WatchList> The updated watchlist after deleting the stock.
     */
    @DeleteMapping(path = "/watchlist/stocks/{symbol}")
    public List<WatchList> deleteStockFromWatchList(@PathVariable String symbol) {
        User user = getLoggedInUser();
        watchListService.deleteStock(user, symbol);
        return user.getWatchList();
    }






}
