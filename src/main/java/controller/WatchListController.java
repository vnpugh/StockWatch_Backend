package controller;

import models.Stock;
import models.WatchList;
import models.request.CreateWatchlistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import service.WatchListService;

import java.util.List;



@RestController
@RequestMapping(path = "/api/watchlist")
public class WatchListController {
    @Autowired
    private WatchListService watchListService;

    @Autowired
    private UserRepository userRepository;

    //**User can view all stocks on their watch list.

    /**
     * GET: endpoint http://localhost:8080/api/watchlist/stocks?id=
     * Retrieves (view) all stocks on the user's watchlist.
     *
     * @param id watchlist id
     * @return a list of stocks on the watchlist
     */
    @GetMapping("/stocks")
    public List<Stock> getAllStocksOnWatchList(@RequestParam(name = "id") Long id) {
        return watchListService.getAllStocksOnWatchList(id);
    }


    //**User can create a custom watch list.

    /**
     * POST: endpoint http://localhost:8080/api/watchlist/create
     * Create a new watchlist for the logged-in user.
     * The new watchlist is saved for the user (User Repository)
     *
     * @return ResponseEntity<WatchList> The created watchlist with HTTP status 201 (Created).
     */
    @PostMapping(path = "/create")
    public ResponseEntity<WatchList> createWatchList(@RequestBody CreateWatchlistRequest createWatchlist) {
        WatchList watchList = watchListService.createWatchlist(createWatchlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(watchList);
    }


//**User can add a stock to their watch list by ticker symbol.

    /**
     * POST: endpoint http://localhost:8080/api/watchlist/addStock?symbol=&watchlist_id=
     * Adds a stock to the user's watchlist by the specified symbol.
     *
     * @param symbol the ticker symbol of the stock to add
     * @return the updated watchlist of the user
     */
    @PostMapping(path = "/addStock")
    public WatchList addStockToWatchList(@RequestParam String symbol, @RequestParam(name = "watchlist_id") Long watchlistId) {
        WatchList watchList = watchListService.addStockToWatchlist(symbol, watchlistId);
        return watchList;
    }

    //**User can update the name of a watch list.

    /**
     * PUT: endpoint http://localhost:8080/api/watchlist/modify?new_name=&watchlist_id=
     * Update the name of a watchlist for the logged-in user.
     *
     * @param watchlistId The ID of the watchlist to update.
     * @param newListName The new name for the watchlist.
     * @return ResponseEntity<WatchList> The updated watchlist with HTTP status 200 (OK).
     * If the watchlist is not found, returns HTTP status 404 (Not Found).
     */
    @PutMapping(path = "/modify")
    public ResponseEntity<WatchList> updateWatchListName(@RequestParam(name = "new_name") String newListName, @RequestParam(name = "watchlist_id") Long watchlistId) {
        WatchList watchList = watchListService.modifyWatchlist(watchlistId, newListName);
        return ResponseEntity.status(HttpStatus.CREATED).body(watchList);
    }


//**User can delete a stock from their watch list.

    /**
     * METHOD: DELETE endpoint http://localhost:8080/api/watchlist/deleteStock?symbol=&watchlist_id=
     * Delete a stock from the user's watchlist by symbol.
     *
     * @param symbol The symbol of the stock to delete.
     * @return List<WatchList> The updated watchlist after deleting the stock.
     */
    @DeleteMapping(path = "/deleteStock")
    public ResponseEntity<WatchList> deleteStockFromWatchList(@RequestParam String symbol, @RequestParam(name = "watchlist_id") Long watchlistId) {
        WatchList watchList = watchListService.deleteStock(symbol, watchlistId);
        return ResponseEntity.status(HttpStatus.CREATED).body(watchList);
    }


}