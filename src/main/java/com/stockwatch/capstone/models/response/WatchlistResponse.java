package com.stockwatch.capstone.models.response;

public class WatchlistResponse {

    private String listName;

    private Long listId;

    public WatchlistResponse(String listName, Long listId) {
        this.listName = listName;
        this.listId = listId;
    }


    @Override
    public String toString() {
        return "WatchlistResponse{" +
                "listName='" + listName + '\'' +
                ", listId=" + listId +
                '}';
    }
}
