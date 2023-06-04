package models;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column(unique = true)
    private String email; //username

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password; //ignore password

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<WatchList> watchList;

    public User() {
    }

    public User(String firstName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WatchList> getWatchList() {
        return watchList;
    }

    public void setWatchList(List<WatchList> watchList) {
        this.watchList = watchList;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", watchList=" + watchList +
                '}';
    }

    public void addWatchList(WatchList watchList) {
        if (watchList == null) {
            watchList = new WatchList();
        }
        watchList.add(watchList);
    }



    public WatchList findWatchListByWatchListId(Long watchListId) {
        for (WatchList watchList : watchList) {
            if (watchList.getWatchListId().equals(watchListId)) {
                return watchList;
            }
        }
        return null;
    }



}
