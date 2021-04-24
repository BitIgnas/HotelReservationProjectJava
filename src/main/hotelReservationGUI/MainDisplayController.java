package hotelReservationGUI;

import hotelReservation.entities.RegisteredUser;
import hotelReservation.registeredUserDao.RegisteredUserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainDisplayController implements Initializable {


    @FXML
    private Button btn_settings;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_add_reservation;

    @FXML
    private Button btn_cancel_reservation;

    @FXML
    private Button btn_hotels;

    @FXML
    private Button btn_rooms;

    @FXML
    private Label usernameLabel;

    private Stage stage;

    RegisteredUser registeredUser;

    @FXML
    void addReservationAction(ActionEvent event) throws IOException {
        stage = Main.getStage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainDisplayReservation.fxml"));
        Parent rootPane = loader.load();

        stage.getScene().setRoot(rootPane);
        stage.setHeight(550);
        stage.setWidth(950);
        stage.setTitle("Grand bookings");
        stage.setResizable(false);

        RegisteredUserDaoImpl registeredUserDao = new RegisteredUserDaoImpl();

        MainDisplayReservationController reservationController = loader.getController();
        reservationController.setUser(registeredUserDao.getUserObjectByUsername(registeredUser.getUsername()));


    }

    @FXML
    void cancelReservationAction(ActionEvent event) throws IOException {
        stage = Main.getStage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainDisplayCancelReservation.fxml"));
        Parent rootPane = loader.load();

        stage.getScene().setRoot(rootPane);
        stage.setHeight(550);
        stage.setWidth(950);
        stage.setTitle("Grand bookings");
        stage.setResizable(false);

        CancelReservationController cancelReservationController = loader.getController();
        cancelReservationController.setUser(registeredUser);
        cancelReservationController.setUserId(registeredUser.getId());
    }

    @FXML
    void logOutAction(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
        Parent rootPane = loader.load();

        stage.getScene().setRoot(rootPane);
        stage.setHeight(450);
        stage.setWidth(750);
        stage.setTitle("Grand bookings");
        stage.setResizable(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void setUsernameLabel(String username){
        usernameLabel.setText(username);
    }

    public void setUser(RegisteredUser user){
        this.registeredUser = user;
    }
}
