package cinema;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cinema {

    private final int rows = 9;
    private final int columns = 9;
    private Map<String, Seat> seatMap;
    private Map<String, Seat> tokenSeatMap;
    private int income = 0;
    private int available = 81;
    private int purchased = 0;

    public void initializeCinema() {
        tokenSeatMap = new ConcurrentHashMap<>();
        seatMap = new ConcurrentHashMap<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                String seatKey = row + "-" + col;
                Seat seat = new Seat(row, col);
                seatMap.put(seatKey, seat);
            }
        }
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public Cinema() {
        initializeCinema();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Map<String, Seat> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(Map<String, Seat> seatMap) {
        this.seatMap = seatMap;
    }

    public Map<String, Seat> getTokenSeatMap() {
        return tokenSeatMap;
    }
}
