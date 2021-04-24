package hotelReservation.hotelDao;


import hotelReservation.entities.Hotel;
import hotelReservation.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.math.BigInteger;
import java.util.List;

public class HotelServiceDaoImpl implements HotelServiceDao {

    @Override
    public List<String> getAllHotelNames() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createSQLQuery("select hotel_name FROM Hotel");
        List<String> hotels = query.getResultList();

        return hotels;
    }

    @Override
    public BigInteger getHotelIdByHotelName(String hotelName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createSQLQuery("select hotel_id FROM hotel WHERE hotel_name = ?");
        query.setParameter(1, hotelName);

        return (BigInteger) query.getResultList().get(0);
    }

    @Override
    public void addNewHotel(Hotel hotel) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createSQLQuery("INSERT INTO Hotel(hotel_name, hotel_Code, rooms, country, available_Rooms) VALUES(:hotel_name, :hotel_Code, :rooms, :country, :available_Rooms)");
        query.setParameter("hotel_name", hotel.getHotelName());
        query.setParameter("hotel_Code", hotel.getHotelCode());
        query.setParameter("rooms", hotel.getHotelRooms());
        query.setParameter("country", hotel.getHotelCountry());
        query.setParameter("available_Rooms", hotel.getAvailableRooms());

        query.executeUpdate();

        session.flush();
        session.close();
    }

    @Override
    public List<Hotel> getAllHotels() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from Hotel");
        List<Hotel> hotels = query.getResultList();

        return hotels;

    }

    @Override
    public  List<String> getUserHotelsById(Long hotelId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createSQLQuery("SELECT hotel_name FROM Hotel WHERE hotel_id = :id");
        query.setParameter("id", hotelId);

        List<String> hotelName = query.getResultList();

        return hotelName;
    }
}
