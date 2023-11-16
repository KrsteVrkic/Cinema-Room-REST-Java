package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
public class CinemaController {

    @Autowired
    private Cinema cinema;

    @GetMapping("/seats")
    public SeatResponse getSeats() {

        SeatResponse seatResponse = new SeatResponse();
        seatResponse.setRows(cinema.getRows());
        seatResponse.setColumns(cinema.getColumns());

        List<SeatData> seatDataList = new ArrayList<>();
        for (Seat seat : cinema.getSeatMap().values()) {
            if (!seat.isBooked()) {
                SeatData seatData = new SeatData(seat.getRow(), seat.getColumn(), seat.getTicketPrice());
                seatDataList.add(seatData);
            }
        }

        seatDataList.sort(Comparator.comparing(SeatData::getRow).thenComparing(SeatData::getColumn));
        seatResponse.setSeats(seatDataList);
        return seatResponse;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> postPurchase(@RequestBody SeatRequest request) {

        String seatKey = request.getRow() + "-" + request.getColumn();
        Seat seat = cinema.getSeatMap().get(seatKey);

        if (seat == null) throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        if (seat.isBooked()) throw new SeatNotAvailableException("The ticket has been already purchased!");

        seat.setBooked(true);
        String token = UUID.randomUUID().toString();
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        SeatData bookedSeatData = new SeatData(seat.getRow(), seat.getColumn(), seat.getTicketPrice());
        purchaseResponse.setTicket(bookedSeatData);
        purchaseResponse.setToken(token);
        int ticketPrice = seat.getTicketPrice();
        cinema.getTokenSeatMap().put(token, seat);
        cinema.setIncome(cinema.getIncome() + ticketPrice);
        cinema.setAvailable(cinema.getAvailable() - 1);
        cinema.setPurchased(cinema.getPurchased() + 1);
        return ResponseEntity.ok(purchaseResponse);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnResponse> postReturn(@RequestBody ReturnRequest request) {

        String token = request.getToken();
        Seat seat = cinema.getTokenSeatMap().get(token);

        if (seat == null) throw new TokenNotFoundException("Wrong token!");
        int ticketPrice = seat.getTicketPrice();
        cinema.getTokenSeatMap().remove(token, seat);
        cinema.setIncome(cinema.getIncome() - ticketPrice);
        cinema.setAvailable(cinema.getAvailable() + 1);
        cinema.setPurchased(cinema.getPurchased() - 1);
        seat.setBooked(false);

        SeatData returnedSeatData = new SeatData(seat.getRow(), seat.getColumn(), seat.getTicketPrice());
        ReturnResponse returnResponse = new ReturnResponse();
        returnResponse.setTicket(returnedSeatData);
        return ResponseEntity.ok(returnResponse);
    }


    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats(@RequestParam(value = "password",
            required = false) String password) {

        String correctPassword = "super_secret";
        if (password == null || !password.equals(correctPassword)) {
            throw new IncorrectPasswordException("The password is wrong!");
        }

        StatsResponse statsResponse = new StatsResponse(cinema.getIncome(), cinema.getAvailable(),
                cinema.getPurchased());
        return ResponseEntity.ok(statsResponse);
    }
}



