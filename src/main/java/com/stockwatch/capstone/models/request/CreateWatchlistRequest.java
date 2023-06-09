package com.stockwatch.capstone.models.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a request to create a watchlist.
 * Implements the Serializable interface to support serialization.
 */
public class CreateWatchlistRequest implements Serializable {

    /**
     * The name of the watchlist.
     */
    @NotEmpty(message = "Name cannot be empty")
    private String listName;

    /**
     * The description of the watchlist.
     */
    private String description;

    /**
     * The IDs of the stocks to be included in the watchlist.
     */
    private List<Integer> stockIds;

    /**
     * Returns the name of the watchlist.
     * @return The name of the watchlist.
     */
    public String getListName() {
        return listName;
    }

    /**
     * Returns the description of the watchlist.
     * @return The description of the watchlist.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the IDs of the stocks in the watchlist.
     * @return The IDs of the stocks in the watchlist.
     */
    public List<Integer> getStockIds() {
        return stockIds;
    } }
