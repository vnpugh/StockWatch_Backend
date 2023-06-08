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
     *
     * @param query
     * @return List of stocks filtered by either symbol or company.
     */
    @GetMapping("/companyOrSymbol")
    public ResponseEntity<List<Stock>> searchStockByCompanyOrSymbol(@RequestParam String query, @RequestParam(required = false, name = "watchlist_id") Long watchlistId) {
        List<Stock> searchResults = stockService.searchStockByCompanyOrSymbol(query, watchlistId);
        return ResponseEntity.ok(searchResults);
    }

    /**
     * METHOD: GET http://localhost:8080/api/stocks
     *
     * @return List of stocks in the system.
     */
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> searchResults = stockService.getAllStocks();
        return ResponseEntity.ok(searchResults);
    }
}
