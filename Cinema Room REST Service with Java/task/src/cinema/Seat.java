package cinema;

public class Seat {

    private int row;
    private int column;
    private int ticketPrice;
    private boolean booked;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.ticketPrice = row <= 4 ? 10 : 8;
        this.booked = false;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
