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

    /**
     * Updates the list name of a watchlist owned by a specific user.
     * @param listName The new list name.
     * @param userId The ID of the user.
     * @param watchlistId  The ID of the watchlist.
     * @return The number of watchlists updated (should be 1 if the update was successful).
     */
    @Modifying
    @Query("UPDATE WatchList w SET w.listName = ?1 WHERE w.user.id = ?2 AND w.id=?3")
    Integer updateListNameByUser(String listName, Long userId, Long watchlistId);

}
