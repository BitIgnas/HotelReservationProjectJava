package hotelReservation.reservationDao;

import hotelReservation.entities.RegisteredUser;

import java.math.BigInteger;
import java.util.List;

public interface ReservationDao {

    public void addReservation(Long userId, BigInteger hotelId, String checkInDate, String checkOutDate);

    public List<String> getAllUserReservationsByUserId(Long id);

    public boolean dateValidation(String date1, String date2);

}
