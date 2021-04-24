package hotelReservationGUI;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import hotelReservation.hibernateUtil.HibernateUtil;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HibernateUtil.getSessionFactory();
        HotelToSave.insertHotel();

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        primaryStage.setTitle("Grand booking login");
        primaryStage.setScene(new Scene(root, 750, 450));
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public void changeApplicationScene(String fxml) throws Exception {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
       launch(args);
    }
}
