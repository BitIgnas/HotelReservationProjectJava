package hotelReservationGUI;

import hotelReservation.entities.RegisteredUser;
import hotelReservation.entities.Reservation;
import hotelReservation.hibernateUtil.HibernateUtil;
import hotelReservation.hotelDao.HotelServiceDaoImpl;
import hotelReservation.registeredUserDao.RegisteredUserDaoImpl;
import hotelReservation.reservationDao.ReservationDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainDisplayReservationController implements Initializable {

    @FXML
    private Button btn_settings;

    @FXML
    private Button btn_logout;

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;

    @FXML
    private MenuButton btn_hotel_menu;

    @FXML
    private Button btn_addReservation;

    @FXML
    private Button btn_cancel;

    @FXML
    private ChoiceBox<String> hotelCheckBox;

    @FXML
    private Label datePickerErrorLog;

    @FXML
    private Label hotelErrorLog;

    @FXML
    private Label reservationLog;

    private RegisteredUser user;

    private Main main = new Main();

    private HotelServiceDaoImpl hotelDao = new HotelServiceDaoImpl();

    private RegisteredUserDaoImpl userDao = new RegisteredUserDaoImpl();

    private ReservationDaoImpl reservationDao = new ReservationDaoImpl();

    private List<String> hotelList = hotelDao.getAllHotelNames();

    private ObservableList<String> hotels = FXCollections.observableList(hotelList);

    @FXML
    void addReservation(ActionEvent event) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();

            if (hotelCheckBox.getSelectionModel().isEmpty()) {
                hotelErrorLog.setText("Please selected a hotel");
            } else if (checkInDate.getValue().getDayOfYear() > checkOutDate.getValue().getDayOfYear()) {
                datePickerErrorLog.setText("Invalid booking date selected");
            } else if (reservationDao.dateValidation(checkInDate.toString(), checkOutDate.toString())) {
                datePickerErrorLog.setText("Invalid booking date selected");
            } else {
               reservationDao.addReservation(
                       user.getId(),
                       hotelDao.getHotelIdByHotelName(hotelCheckBox.getValue().toString()),
                       checkInDate.getValue().toString(),
                       checkOutDate.getValue().toString()
               );

              userDao.assignUserToHotel(hotelDao.getHotelIdByHotelName(hotelCheckBox.getValue()), user.getUsername());

              reservationLog.setText("Booking successfully added to " + hotelCheckBox.getValue());
              setReturnToMenuButton();
              hotelErrorLog.setVisible(false);
              datePickerErrorLog.setVisible(false);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cancelAction(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainDisplay.fxml"));
            stage = (Stage) btn_cancel.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            MainDisplayController mainDisplay = loader.getController();
            mainDisplay.setUser(user);
            mainDisplay.setUsernameLabel(user.getFirstname());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void logOutAction(ActionEvent event) throws Exception {
        Stage stage = Main.getStage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
        Parent rootPane = loader.load();

        stage.getScene().setRoot(rootPane);
        stage.setHeight(450);
        stage.setWidth(750);
        stage.setTitle("Grand bookings");
        stage.setResizable(false);

    }

    @FXML
    void settingsAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotelCheckBox.setItems(hotels);
    }

    void setUser(RegisteredUser registeredUser) {
        this.user = registeredUser;
    }

    void setReturnToMenuButton(){ btn_cancel.setText("Return To Menu"); }
}

