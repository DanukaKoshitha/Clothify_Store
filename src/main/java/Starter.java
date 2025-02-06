import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;


public class Starter extends Application {
    public static void main(String[] args) {

        String key = "12345";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String password = "danuka1234";

        String encript = basicTextEncryptor.encrypt(password);

        String decript = basicTextEncryptor.decrypt(encript);

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("View/LoginForm.fxml"))));
        stage.setTitle("Login Form");
        stage.show();
    }
}
