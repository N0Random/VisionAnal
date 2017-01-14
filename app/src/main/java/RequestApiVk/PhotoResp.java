package RequestApiVk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Brom on 05.01.2017.
 */
public class PhotoResp {
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
