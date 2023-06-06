package models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "watchlist")
public class WatchList {


    @Column(name = "watchlist_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchListId;

    @Column
    private String listName;

    @Column
    private LocalDate dateCreated;

    @Column
    private String description;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "watchlist_stocks",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<Stock> stocks;


//    @OneToMany
//    private List<Stock> stocks;


    public WatchList(Long watchListId, String listName, LocalDate dateCreated, String description) {
        this.watchListId = watchListId;
        this.listName = listName;
        this.dateCreated = dateCreated;
        this.description = description;
    }


    public WatchList() {
        this.stocks = new ArrayList<>();
    }


    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> list) {
        this.stocks = list;
    }

    public Long getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(Long watchListId) {
        this.watchListId = watchListId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setListName() {
        this.listName = listName;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WatchList{" +
                "watchListId=" + watchListId +
                ", listName='" + listName + '\'' +
                ", dateCreated=" + dateCreated +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", stocks=" + stocks +
                '}';
    }

    public void add(WatchList watchList) {
    }
}
