package hotelReservationGUI;

import hotelReservation.entities.Hotel;
import hotelReservation.hibernateUtil.HibernateUtil;
import org.hibernate.Session;

public class HotelToSave {

    public static void insertHotel(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Bookin Inn");
        hotel1.setHotelCode("3424");
        hotel1.setHotelRooms(255);
        hotel1.setHotelCountry("Lithuania");
        hotel1.setAvailableRooms(167);

        Hotel hotel2 = new Hotel();
        hotel2.setHotelName("Konarsky");
        hotel2.setHotelCode("3216");
        hotel2.setHotelRooms(121);
        hotel2.setHotelCountry("Lithuania");
        hotel2.setAvailableRooms(58);

        Hotel hotel3 = new Hotel();
        hotel3.setHotelName("Pass Hotel");
        hotel3.setHotelCode("1234");
        hotel3.setHotelRooms(67);
        hotel3.setHotelCountry("Latvia");
        hotel3.setAvailableRooms(56);

        Hotel hotel4 = new Hotel();
        hotel4.setHotelName("Urban hotels");
        hotel4.setHotelCode("2345");
        hotel4.setHotelRooms(111);
        hotel4.setHotelCountry("USA");
        hotel4.setAvailableRooms(89);


        session.save(hotel1);
        session.save(hotel2);
        session.save(hotel3);
        session.save(hotel4);
    }
}
