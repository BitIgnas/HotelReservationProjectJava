
import hotelReservation.entities.Hotel;
import hotelReservation.entities.RegisteredUser;
import hotelReservation.hibernateUtil.HibernateUtil;
import hotelReservation.hotelDao.HotelServiceDaoImpl;
import hotelReservation.registeredUserDao.RegisteredUserDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

public class HibernateTest {

    private static SessionFactory sessionFactory;
    private RegisteredUserDaoImpl userDao = new RegisteredUserDaoImpl();
    private HotelServiceDaoImpl hotelDao = new HotelServiceDaoImpl();
    private Session session;

    @BeforeAll
    public static void hibernateSetup() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @AfterAll
    public static void hibernateTearDown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }


    @Test
    public void userInsertTest() {
        session.getTransaction().begin();

        RegisteredUser user = new RegisteredUser();
        user.setFirstname("TestName");
        user.setLastname("TestLastName");
        user.setUsername("Test");
        user.setPassword("Test");

        userDao.addNewUser(user);

        List<RegisteredUser> users = userDao.getAllRegisteredUser();

        Assertions.assertEquals(1, users.size());
    }

    @Test
    public void hotelInsertTest(){
        session.getTransaction().begin();

        Hotel hotel = new Hotel();
        hotel.setHotelName("Konarsky");
        hotel.setHotelCountry("France");
        hotel.setHotelCode("KON");
        hotel.setAvailableRooms(255);
        hotel.setHotelRooms(300);

        hotelDao.addNewHotel(hotel);

        List<Hotel> hotels = hotelDao.getAllHotels();

        Assertions.assertEquals(1, hotels.size());
    }


    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
    }

    @AfterEach
    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }
}
