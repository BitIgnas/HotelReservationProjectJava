package hotelReservation.registeredUserDao;

import hotelReservation.entities.RegisteredUser;
import hotelReservation.hibernateUtil.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;

public class RegisteredUserDaoImpl implements RegisteredUserDao{

    public boolean checkIfUserExist(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<RegisteredUser> criteriaQuery = criteriaBuilder.createQuery(RegisteredUser.class);
        Root<RegisteredUser> userRoot = criteriaQuery.from(RegisteredUser.class);

        Predicate usernamePredicate = criteriaBuilder.equal(userRoot.get("username"), username);
        Predicate passwordPredicate = criteriaBuilder.equal(userRoot.get("password"), password);

        Predicate resultPredicate = criteriaBuilder.and(passwordPredicate, usernamePredicate);

        Query query = session.createQuery(criteriaQuery);
        List<RegisteredUser> userList = query.getResultList();

        if(userList.size() != 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void assignUserToHotel(BigInteger hotelId, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createSQLQuery("UPDATE registeredUser SET hotel_id = :id WHERE username = :name");
        query.setParameter("id", hotelId);
        query.setParameter("name", username);

        query.executeUpdate();

        session.flush();
        session.close();
    }

    @Override
    public void addNewUser(RegisteredUser user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        session.save(user);

        session.flush();
        session.close();
    }


    public List<RegisteredUser> getAllRegisteredUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from RegisteredUser");
        List<RegisteredUser> users = query.getResultList();

        return users;
    }



    @Override
    public boolean authenticate(String username, String password) {

        boolean confirmation = false;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();

            String sql = "Select * FROM RegisteredUser WHERE username = ?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(1, username);

            RegisteredUser user = (RegisteredUser) query.getResultList().get(0);

            confirmation = (user != null && user.getPassword().equals(password));

            return confirmation;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return confirmation;
        }

    }

    @Override
    public String getNameByUserName(String username) {
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.getTransaction().begin();

       CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
       CriteriaQuery<RegisteredUser> userQuery = criteriaBuilder.createQuery(RegisteredUser.class);
       Root<RegisteredUser> userRoot = userQuery.from(RegisteredUser.class);

       userQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("username"), username));
       Query query = session.createQuery(userQuery);

       RegisteredUser user = (RegisteredUser) query.getResultList().get(0);

       return user.getFirstname();
    }

    public RegisteredUser getUserObjectByUsername(String username){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();


        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<RegisteredUser> userQuery = criteriaBuilder.createQuery(RegisteredUser.class);
        Root<RegisteredUser> userRoot = userQuery.from(RegisteredUser.class);

        userQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("username"), username));
        Query query = session.createQuery(userQuery);

        RegisteredUser user = (RegisteredUser) query.getResultList().get(0);

        return user;
    }
}
