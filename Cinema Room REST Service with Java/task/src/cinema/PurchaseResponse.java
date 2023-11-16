package cinema;

public class PurchaseResponse {
    private String token;
    private SeatData ticket;

    // getters and setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SeatData getTicket() {
        return ticket;
    }

    public void setTicket(SeatData ticket) {
        this.ticket = ticket;
    }
}