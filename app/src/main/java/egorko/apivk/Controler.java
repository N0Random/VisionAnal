package egorko.apivk;

import java.util.List;

import RequestApiVk.Item;
import RequestApiVk.Photo;

/**
 * Created by Brom on 05.01.2017.
 */
public class Controler {


    private Photo respData;
    private List<Item> Data;
    private int position;
    private  int count;
    public  Controler(){
        position=0;
        count=0;
    };
    public  Controler(List<Item> _data)
    {

        position=0;
        Data=_data;
        count=_data.size();
    }
   public Item nextItem()  {

       if(position+1<count)
           position++;
       else
           position=0;

       return Data.get(position);
   }
    public Item previousItem()  {

        if(position-1>=0)
            position--;
        else
           position=count-1;
        return Data.get(position);
    }


////get & set
public Item GetCurrentItem(){
    return Data.get(position);
}
    public String getNotNullUrl(){
        Item curItem=Data.get(position);
     if(curItem.getPhoto1280()!=null)
            return curItem.getPhoto1280();
        else if(curItem.getPhoto807()!=null)
            return curItem.getPhoto807();
        else if(curItem.getPhoto604()!=null)
            return curItem.getPhoto604();
        else if(curItem.getPhoto130()!=null)
            return curItem.getPhoto130();
        else return curItem.getPhoto75();
    };
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Item> getData() {
        return Data;
    }

    public void setData(List<Item> data) {
        position=0;
        count=data.size();
        Data = data;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public Photo getRespData() {
        return respData;
    }

    public void setRespData(Photo respData) {
        this.respData = respData;
    }
}
