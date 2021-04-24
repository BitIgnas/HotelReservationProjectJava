package hotelReservation.hotelDao;

import hotelReservation.entities.Hotel;

import java.math.BigInteger;
import java.util.List;

public interface HotelServiceDao {

    public List<String> getAllHotelNames();

    public BigInteger getHotelIdByHotelName(String hotelName);

    public void addNewHotel(Hotel hotel);

    public List<Hotel> getAllHotels();

    public  List<String> getUserHotelsById(Long userId);






}


