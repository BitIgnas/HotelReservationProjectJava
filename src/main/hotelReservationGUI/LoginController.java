package hotelReservationGUI;

import hotelReservation.hibernateUtil.HibernateUtil;
import hotelReservation.registeredUserDao.RegisteredUserDaoImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    RegisteredUserDaoImpl registeredUserDao = new RegisteredUserDaoImpl();

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btm_signIn;

    @FXML
    private Button btm_signUp;

    @FXML
    private Label errorLog;



    @FXML
    void logInAction() throws IOException {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery("SELECT * FROM RegisteredUser WHERE username = ? AND password = ?");
            query.setParameter(1, username.getText());
            query.setParameter(2, password.getText());

            int user = query.getResultList().size();

            if (user > 0) {
                errorLog.setText("Login successful");
                Thread.sleep(700); //for smooth effect :)

                Stage stage = Main.getStage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainDisplay.fxml"));
                Parent rootPane = loader.load();
                stage.getScene().setRoot(rootPane);
                stage.setHeight(550);
                stage.setWidth(950);
                stage.setTitle("Grand bookings");
                stage.setResizable(false);

                MainDisplayController mainDisplayController = loader.getController();
                mainDisplayController.setUser(registeredUserDao.getUserObjectByUsername(username.getText()));
                mainDisplayController.setUsernameLabel(registeredUserDao.getNameByUserName(username.getText()));

            } else {
                errorLog.setText("Wrong username or password");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUpAction() throws Exception {
        Main main = new Main();
        Thread.sleep(350);
        main.changeApplicationScene("registerForm.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}



