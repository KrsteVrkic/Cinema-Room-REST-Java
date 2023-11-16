package cinema;

import java.util.List;

public class SeatResponse {

    private int rows;
    private int columns;
    private List<SeatData> seats;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<SeatData> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatData> seats) {
        this.seats = seats;
    }
}
