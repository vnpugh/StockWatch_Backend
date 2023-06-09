package com.stockwatch.capstone.controller;


import com.stockwatch.capstone.models.Stock;
import com.stockwatch.capstone.repository.StockRepository;
import com.stockwatch.capstone.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/stocks")
public class StockController {

    private StockRepository stockRepository;
    private StockService stockService;


    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * METHOD: GET endpoint: http://localhost:8080/api/stocks/companyOrSymbol?company=&symbol=
     * This endpoint searches for stocks based on the provided query, which can be either
     * a company name or a stock symbol.
     * @param query The search query, representing either a company name or stock symbol
     * @param watchlistId (Optional) The ID of the watchlist to restrict the search within
     * @return ResponseEntity containing a list of Stock objects matching the search query
     * @see Stock
     */
    @GetMapping("/companyOrSymbol")
    public ResponseEntity<List<Stock>> searchStockByCompanyOrSymbol(@RequestParam String query, @RequestParam(required = false, name = "watchlist_id") Long watchlistId) {
        List<Stock> searchResults = stockService.searchStockByCompanyOrSymbol(query, watchlistId);
        return ResponseEntity.ok(searchResults);
    }


    /**
     * METHOD: GET http://localhost:8080/api/stocks
     * Retrieves all stocks.
     * This endpoint returns a list of all stocks available in the system.
     * @return ResponseEntity containing a list of Stock objects representing all stocks
     * @see Stock
     */
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> searchResults = stockService.getAllStocks();
        return ResponseEntity.ok(searchResults);
    }
}
