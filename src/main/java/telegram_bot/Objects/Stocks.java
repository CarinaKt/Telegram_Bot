package telegram_bot.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stocks implements Serializable{
    // private Stock[] stocks;
    private List<String> stocksList;

    public Stocks(){

        // this.stocks = new Stock[0];
        this.stocksList = new ArrayList<>();
    }
    
    // public Stock[] getStocks() {
    //     return stocks;
    // }
    
    public List<String>  getStocks() {
        return stocksList;
    }


    // private boolean exists(String stock){
    //     for (Stock s : stocks) {
    //         if (s.getSymbol() == stock){
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public void add(String stock){
        try {
            this.stocksList.add(stock);
        } catch (Exception e) {
            // TODO: handle exception
        }
        

        // if (!exists(stock)){
        //     // create a new array with one extra element
        //     Stock[] newStocks = new Stock[stocks.length + 1];

        //     // copy the elements from the old array to the new array
        //     System.arraycopy(stocks, 0, newStocks, 0, stocks.length);

        //     // add a new ChatID object to the end of the new array
        //     newStocks[newStocks.length - 1] = new Stock(stock);

        //     // update the reference to the new array
        //     stocks = newStocks;
        // }
    }

    // private int getIndex(String stock) throws Exception{
    //     int index = 0;
    //     for (Stock s : stocks) {
    //        if (s.getSymbol() == stock){
    //         return index;
    //        }
    //        index ++;
    //     }
    //     throw new Exception("ID not in Array");
    // }

    public void remove(String stock){
        try {
            int index =this.stocksList.indexOf(stock);
            this.stocksList.remove(index);
        } catch (Exception e) {
            // TODO: handle exception
        }
  
        // try {
        //     // find the index of the object to be removed
        //     int indexToRemove = getIndex(stock);

        //     // create a new array with one fewer element
        //     Stock[] newStocks = new Stock[stocks.length - 1];

        //     // copy the elements from the old array to the new array, excluding the element to be removed
        //     System.arraycopy(stocks, 0, newStocks, 0, indexToRemove);
        //     System.arraycopy(stocks, indexToRemove + 1, newStocks, indexToRemove, stocks.length - indexToRemove - 1);

        //     // update the reference to the new array
        //     stocks = newStocks;
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
    }
}
