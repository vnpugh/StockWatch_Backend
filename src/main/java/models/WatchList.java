package models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Long description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public WatchList() {
    }

    public WatchList(Long watchListId, String listName, LocalDate dateCreated, Long description) {
        this.watchListId = watchListId;
        this.listName = listName;
        this.dateCreated = dateCreated;
        this.description = description;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getDescription() {
        return description;
    }

    public void setDescription(Long description) {
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
}
