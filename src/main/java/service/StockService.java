package service;

import models.Stock;
import org.springframework.stereotype.Service;
import repository.StockRepository;

import java.util.List;


@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Filter stock by symbol
     *
     * @param symbol
     * @return Stock that matches symbol.
     */
    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbolIgnoreCase(symbol);
    }

    /**
     * Search stocks by symbol or company
     *
     * @param company
     * @param symbol
     * @return List of stocks matching the query
     */
    public List<Stock> searchStockByCompanyOrSymbol(String company, String symbol) {
        return stockRepository.findByCompanyEqualsIgnoreCaseOrSymbolEqualsIgnoreCase(company, symbol);
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