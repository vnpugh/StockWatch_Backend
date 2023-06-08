package com.stockwatch.capstone.models.response;

public class WatchlistResponse {

    private String listName;

    private Long listId;

    public WatchlistResponse(String listName, Long listId) {
        this.listName = listName;
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
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
