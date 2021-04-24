package hotelReservation.reservationDao;

import hotelReservation.entities.RegisteredUser;
import hotelReservation.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {


    @Override
    public void addReservation(Long userId, BigInteger hotelId, String checkInDate, String checkOutDate) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createSQLQuery("INSERT INTO Reservations(userId, hotelId, check_In, check_out) VALUES (?,?,?,?)");
        query.setParameter(1, userId);
        query.setParameter(2, hotelId);
        query.setParameter(3, checkInDate);
        query.setParameter(4, checkOutDate);

        query.executeUpdate();

        session.flush();
        session.close();
    }

    @Override
    public List<String> getAllUserReservationsByUserId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createSQLQuery("SELECT check_In FROM reservations WHERE userId = ?");
        query.setParameter(1, id);

        List<String> userReservation = query.getResultList();

        return userReservation;
    }

    @Override
    public boolean dateValidation(String date1, String date2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createSQLQuery("SELECT * FROM Reservations WHERE check_In = :checkIn AND check_out = :checkOut");
        query.setParameter("checkIn", date1);
        query.setParameter("checkOut", date2);

        List<String> dates = query.getResultList();

        if(dates.size() < 0){
            return true;
        } else {
            return false;
        }
    }
}
