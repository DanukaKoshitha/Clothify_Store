import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Frame implements Initializable {
    public AnchorPane loadfom;
    public Label lblDate;
    public Label lblTime;

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void loadDashboard() throws IOException {
        URL url = this.getClass().getResource("View/Dashboard.fxml");

        assert url != null;

        Parent load = FXMLLoader.load(url);
        this.loadfom.getChildren().clear();
        this.loadfom.getChildren().add(load);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnProductOnAction(ActionEvent actionEvent) {
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        URL url = this.getClass().getResource("View/User.fxml");

        assert url != null;

        Parent load = FXMLLoader.load(url);
        this.loadfom.getChildren().clear();
        this.loadfom.getChildren().add(load);
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        loadDashboard();
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) lblDate.getScene().getWindow();

        try {
            Scene newScene = new Scene(
                    FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"))

            );

            stage.setScene(newScene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadDashboard();
            getDate();
            getTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDate(){
        LocalDate date = LocalDate.now();
        String stringDate = date.toString();
        lblDate.setText(stringDate);
        return stringDate;
    }

    public String getTime(){
        LocalTime time = LocalTime.now();
        String stringTime = time.toString();
        lblTime.setText(stringTime);
        return stringTime;
    }
}
