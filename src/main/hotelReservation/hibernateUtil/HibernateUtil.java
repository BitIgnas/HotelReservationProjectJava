package hotelReservation.hibernateUtil;

import hotelReservation.entities.Hotel;
import hotelReservation.entities.RegisteredUser;
import hotelReservation.entities.Reservation;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        try {
            if(sessionFactory == null){
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hotelReservations?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Lietkabelis123");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Hotel.class);
                configuration.addAnnotatedClass(RegisteredUser.class);
                configuration.addAnnotatedClass(Reservation.class);

                StandardServiceRegistry serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(settings).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return sessionFactory;
    }
}
