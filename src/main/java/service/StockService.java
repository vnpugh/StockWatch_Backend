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
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks) {
            Double currentPrice = stock.getPrice();
            Double targetPrice = stock.getTargetPrice();

            if (currentPrice != null && targetPrice != null) {
                if (currentPrice < targetPrice) {
                    System.out.println(stock.getSymbol() + " is below the target price of " + targetPrice);
                } else if (currentPrice > targetPrice) {
                    System.out.println(stock.getSymbol() + " is above the target price of " + targetPrice);
                } else {
                    System.out.println(stock.getSymbol() + " is at the target price of " + targetPrice);
                }
            }
        }
    }


    public void setPriceAlert(String symbol, double targetPrice) {
        Stock stock = stockRepository.findBySymbolIgnoreCase(symbol);

        if (stock != null) {
            stock.setTargetPrice(targetPrice);
            stockRepository.save(stock);
        }
    }













}
