package service;

import exceptions.StockNotFoundException;
import models.Stock;
import org.springframework.stereotype.Service;
import repository.StockRepository;

@Service
public class StockService {

    private StockRepository stockRepository;
    private StockService StocksDataLoader;

    public Stock getStockBySymbol(String symbol) {


        Stock stock = StocksDataLoader.getStockBySymbol(symbol);

        if (stock == null) {
            throw new StockNotFoundException("Stock not found for symbol: " + symbol);
        }

        return stock;

    }














}
