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

    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbolIgnoreCase(symbol);
    }

    public List<Stock> searchStockByCompanyOrSymbol(String query) {
        return stockRepository.findByCompanyContainingIgnoreCaseOrSymbolContainingIgnoreCase(query, query);
    }


    public void checkPriceAlerts() {
    }
}
