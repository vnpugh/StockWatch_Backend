package com.stockwatch.capstone.repository;

import com.stockwatch.capstone.models.User;
import com.stockwatch.capstone.models.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Long> {
    List<WatchList> findByUser(User user);

    WatchList findByListName(String listName);

    WatchList findByWatchListIdAndUser(Long watchlistId, User user);

    @Modifying
    @Query("UPDATE WatchList w SET w.listName = ?1 WHERE w.user.id = ?2 AND w.id=?3")
    Integer updateListNameByUser(String listName, Long userId, Long watchlistId);
}
