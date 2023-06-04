package data;


import models.Stock;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repository.StockRepository;
import repository.UserRepository;
import repository.WatchListRepository;
import service.StockService;
import service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;

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

        BigDecimal marketCap1 = new BigDecimal("2.85E12");
        BigDecimal marketCap2 = new BigDecimal("1.85E12");
        BigDecimal marketCap3 = new BigDecimal("1.98E12");
        BigDecimal marketCap4 = new BigDecimal("6.71E11");
        BigDecimal marketCap5 = new BigDecimal("1.38E11");
        BigDecimal marketCap6 = new BigDecimal("4.88E11");
        BigDecimal marketCap7 = new BigDecimal("4.4E10");
        BigDecimal marketCap8 = new BigDecimal("3.58E10");
        BigDecimal marketCap9 = new BigDecimal("3.82E11");
        BigDecimal marketCap10 = new BigDecimal("9.01E11");

        Stock stock1 = new Stock(101,"Apple Inc.", "AAPL", 180.09, 2.84, 4.5, marketCap1);
        stockRepository.save(stock1);
        Stock stock2 = new Stock(102,"Alphabet Inc.", "GOOGL", 1500.0, 10.0, 4.0, marketCap2);
        stockRepository.save(stock2);
        Stock stock3 = new Stock(103, "Microsoft Corporation", "MSFT", 300.0, 5.0, 4.2, marketCap3);
        stockRepository.save(stock3);
        Stock stock4 = new Stock(104, "Berkshire Hathaway", "BRK.A", 421500.0, 5200.0, 4.3, marketCap4);
        stockRepository.save(stock3);
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



        User user = new User("Jane", "email100@gmail.com", "password100");
        userRepository.save(user);







    }


}
