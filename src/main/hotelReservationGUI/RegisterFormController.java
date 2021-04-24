package hotelReservationGUI;

import hotelReservation.entities.RegisteredUser;
import hotelReservation.emailValidation.Validation;
import hotelReservation.registeredUserDao.RegisteredUserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import hotelReservation.phoneValidation.PhoneValidation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {

    @FXML
    private TextField input_username;

    @FXML
    private TextField input_passowrd;

    @FXML
    private TextField input_firstname;

    @FXML
    private TextField input_lastname;

    @FXML
    private TextField input_email;

    @FXML
    private TextField input_phoneNum;

    @FXML
    private Button btn_register;

    @FXML
    private Button btn_menu;

    @FXML
    private Label errorLabel;

    @FXML
    private Label registerLog;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label phoneErrorLabel;

    private RegisteredUserDaoImpl registeredUserDao = new RegisteredUserDaoImpl();

    private RegisteredUser newUser = new RegisteredUser();

    @FXML
    void registerAction(ActionEvent event) throws IOException {
        newUser.setUsername(input_username.getText().toString());
        newUser.setPassword(input_passowrd.getText().toString());
        newUser.setFirstname(input_firstname.getText().toString());
        newUser.setLastname(input_lastname.getText().toString());
        newUser.setUserEmail(input_email.getText().toString());
        newUser.setUserPhoneNumber(input_phoneNum.getText());

        //this is the dumbest way of doing check of text fields, but it works ;).
        if (input_username.getText().isEmpty()) {
            errorLabel.setText("Please enter all fields");
        } else if (input_passowrd.getText().isEmpty()) {
            errorLabel.setText("Please enter all fields");
        } else if (input_firstname.getText().isEmpty()) {
            errorLabel.setText("Please enter all fields");
        } else if (input_lastname.getText().isEmpty()) {
            errorLabel.setText("Please enter all fields");
        } else if (input_email.getText().isEmpty() || !Validation.validate(input_email.getText())) {
            errorLabel.setText("Please enter all fields");
            emailErrorLabel.setText("Invalid email");
        } else if (input_phoneNum.getText().isEmpty() || !PhoneValidation.validatePhoneNumber(input_phoneNum.getText())) {
            errorLabel.setText("Please enter all fields");
            phoneErrorLabel.setText("Invalid phone number");
        } else {
            registeredUserDao.addNewUser(newUser);
            phoneErrorLabel.setVisible(false);
            errorLabel.setVisible(false);
            emailErrorLabel.setVisible(false);
            registerLog.setText("User has been successfully registered");
        }
    }


    @FXML
    void menuReturnAction(ActionEvent event) throws Exception {
        Main main = new Main();
        main.changeApplicationScene("loginForm.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
