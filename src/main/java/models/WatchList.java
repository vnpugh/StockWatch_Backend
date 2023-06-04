package models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="watchlist")
public class WatchList {



    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchListId;

    @Column
    private String listName;

    @Column
    private LocalDate dateCreated;

    @Column
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private List<Stock> stocks;


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

    public Long getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(Long watchListId) {
        this.watchListId = watchListId;
    }

    public String getListName() {
        return listName;
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
                ", description=" + description +
                ", user=" + user +
                '}';
    }




    public void add(WatchList watchList) {
    }

}
