package controller;


import models.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import repository.StockRepository;
import service.StockService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
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

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> searchStockByCompanyOrSymbol(@RequestParam(required = false) String query) {
        List<Stock> searchResults = stockService.searchStockByCompanyOrSymbol(query);
        return ResponseEntity.ok(searchResults);
    }







}
