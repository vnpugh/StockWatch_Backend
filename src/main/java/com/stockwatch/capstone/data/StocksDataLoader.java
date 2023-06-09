package com.stockwatch.capstone.data;


import com.stockwatch.capstone.models.Industry;
import com.stockwatch.capstone.models.Stock;
import com.stockwatch.capstone.models.User;
import com.stockwatch.capstone.models.WatchList;
import com.stockwatch.capstone.repository.StockRepository;
import com.stockwatch.capstone.repository.UserRepository;
import com.stockwatch.capstone.repository.WatchListRepository;
import com.stockwatch.capstone.service.StockService;
import com.stockwatch.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class StocksDataLoader implements CommandLineRunner {

    LocalDate localDate = LocalDate.of(2023, 6, 2);
    LocalDate currentDate = localDate.now();

    private StockRepository stockRepository;
    private StockService stockService;
    private UserRepository userRepository;
    private WatchListRepository watchListRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Autowired
    public void setWatchListRepository(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadStocksData();
    }

    private void loadStocksData() {
        System.out.println("Calling StocksDataLoader");

        BigDecimal marketCap1 = new BigDecimal("91.99E1");
        BigDecimal marketCap2 = new BigDecimal("1.85E12");
        BigDecimal marketCap3 = new BigDecimal("1.98E12");
        BigDecimal marketCap4 = new BigDecimal("6.71E11");
        BigDecimal marketCap5 = new BigDecimal("1.38E11");
        BigDecimal marketCap6 = new BigDecimal("4.88E11");
        BigDecimal marketCap7 = new BigDecimal("4.4E10");
        BigDecimal marketCap8 = new BigDecimal("3.58E10");
        BigDecimal marketCap9 = new BigDecimal("3.82E11");
        BigDecimal marketCap10 = new BigDecimal("9.01E11");
        BigDecimal marketCap11 = new BigDecimal("1.51E06");
        BigDecimal marketCap12 = new BigDecimal("1.85E12");
        BigDecimal marketCap13 = new BigDecimal("5.05E12");
        BigDecimal marketCap14 = new BigDecimal("2.04E02");
        BigDecimal marketCap15 = new BigDecimal("2.10E09");



        Stock stock1 = new Stock(101, "CVS Health Corporation", "CVS", 71.75, 2.14, 4.5, marketCap1);
        stockRepository.save(stock1);
        Stock stock2 = new Stock(102, "Alphabet Inc.", "GOOGL", 1500.0, 10.0, 4.0, marketCap2);
        stockRepository.save(stock2);
        Stock stock3 = new Stock(103, "Microsoft Corporation", "MSFT", 300.0, 5.0, 4.2, marketCap3);
        stockRepository.save(stock3);
        Stock stock4 = new Stock(104, "Berkshire Hathaway", "BRK.A", 421500.0, 5200.0, 4.3, marketCap4);
        stockRepository.save(stock4);
        Stock stock5 = new Stock(105, "Target Corporation", "TGT", 230.0, 2.5, 4.0, marketCap5);
        stockRepository.save(stock5);
        Stock stock6 = new Stock(106, "Walmart Inc.", "WMT", 140.0, 1.8, 4.1, marketCap6);
        stockRepository.save(stock6);
        Stock stock7 = new Stock(107, "Carvana Co.", "CVNA", 350.0, 4.2, 4.2, marketCap7);
        stockRepository.save(stock7);
        Stock stock8 = new Stock(108, "Chewy Inc.", "CHWY", 100.0, 1.5, 3.9, marketCap8);
        stockRepository.save(stock8);
        Stock stock9 = new Stock(109, "Netflix Inc.", "NFLX", 500.0, 5.7, 4.0, marketCap9);
        stockRepository.save(stock9);
        Stock stock10 = new Stock(110, "Facebook Inc.", "FB", 330.0, 3.9, 4.3, marketCap10);
        stockRepository.save(stock10);
        Stock stock11 = new Stock(111, "Nano Labs Ltd", "NA", 2.72, 1.3, 4.0, marketCap11);
        stockRepository.save(stock11);
        Stock stock12 = new Stock(112, "Apple Inc.", "AAPL", 180.09, 2.84, 4.5, marketCap12);
        stockRepository.save(stock12);
        Stock stock13 = new Stock(113, "Wayfair Inc.", "W", 52.95, 7.24, 3.8, marketCap13);
        stockRepository.save(stock13);
        Stock stock14 = new Stock(114, "Dutch Bros, Inc.", "BROS", 52.95, 7.24, 3.8, marketCap13);
        stockRepository.save(stock14);
        Stock stock15 = new Stock(115, "Wayfair Inc.", "W", 30.24, 3.34, 3.9, marketCap14);
        stockRepository.save(stock13);


        User user = new User("Jane", "email100@gmail.com", "password100");
        userRepository.save(user);
        WatchList watchList = new WatchList();
        watchList.setUser(user);
        watchList.setStocks(Arrays.asList(stock1, stock2, stock3));
        user.setWatchLists(Collections.singletonList(watchList));


        // Create stock industries
        List<Industry> industry = new ArrayList<>();
        Industry industry1 = new Industry("Technology");
        Industry industry2 = new Industry("Real Estate");
        Industry industry3 = new Industry("Communication Services");
        Industry industry4 = new Industry("Consumer Staples");
        Industry industry5 = new Industry("Utilities");
        Industry industry6 = new Industry("Healthcare");
        Industry industry7 = new Industry("Finance");
        Industry industry8 = new Industry("Industrials");
        Industry industry9 = new Industry("Energy");
        Industry industry10 = new Industry("Materials");
        Industry industry11 = new Industry("Consumer Discretionary");

        industry.add(industry1);
        industry.add(industry2);
        industry.add(industry3);
        industry.add(industry4);
        industry.add(industry5);
        industry.add(industry6);
        industry.add(industry7);
        industry.add(industry8);
        industry.add(industry9);
        industry.add(industry10);
        industry.add(industry11);


    }


}