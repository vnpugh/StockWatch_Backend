package models.request;

import java.io.Serializable;
import java.util.List;

public class CreateWatchlistRequest implements Serializable {
    private String listName;

    private String description;

    private List<Integer> stockIds;

    @Override
    public String toString() {
        return "CreateWatchlistRequest{" +
                "listName='" + listName + '\'' +
                ", description='" + description + '\'' +
                ", stocks=" + stockIds +
                '}';
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getStockIds() {
        return stockIds;
    }

    public void setStockIds(List<Integer> stockIds) {
        this.stockIds = stockIds;
    }
}
