package hotelReservationGUI;

import hotelReservation.entities.RegisteredUser;
import hotelReservation.hotelDao.HotelServiceDaoImpl;
import hotelReservation.reservationDao.ReservationDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CancelReservationController implements Initializable {

    @FXML
    private Button btn_remove;

    @FXML
    private Button btn_return_to_menu;

    @FXML
    private ListView<String> userReservations;

    @FXML
    private ChoiceBox<String> userHotelChoiceBox;

    private Long userId;

    private RegisteredUser user;

    private HotelServiceDaoImpl hotelDao = new HotelServiceDaoImpl();

    private ReservationDaoImpl reservationDao = new ReservationDaoImpl();

    private List<String> reservationDateList = reservationDao.getAllUserReservationsByUserId(userId);

    private ObservableList<String> reservationDates = FXCollections.observableList(reservationDateList);

    private List<String> hotelList = hotelDao.getAllHotelNames();

    private ObservableList<String> hotelNames = FXCollections.observableList(hotelList);

    @FXML
    void removeBookingAction(ActionEvent event) {

    }

    @FXML
    void removeReservation(MouseEvent event) {

    }

    @FXML
    void returnToMenuAction(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainDisplay.fxml"));
        Parent rootPane = loader.load();

        stage.getScene().setRoot(rootPane);
        stage.setHeight(550);
        stage.setWidth(950);
        stage.setTitle("Grand bookings");
        stage.setResizable(false);

        MainDisplayController mainController = loader.getController();
        mainController.setUser(user);
        mainController.setUsernameLabel(user.getFirstname());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userReservations.setItems(reservationDates);
    }



    public void setUser(RegisteredUser registeredUser) {
        this.user = registeredUser;
    }

    public void setUserId(Long id){
        this.userId = id;
    }
}

