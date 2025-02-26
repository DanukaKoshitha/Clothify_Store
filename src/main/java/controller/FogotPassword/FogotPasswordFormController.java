package controller.FogotPassword;

import util.ServiceType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.Coustom.ForgotPasswordService;
import services.ServiceFactory;

public class FogotPasswordFormController {

    public TextField emailField;
    public TextField otpField;
    public PasswordField newPasswordField;
    public Label statusLabel;

    private String generatedOTP;
    private String userEmail;

    ForgotPasswordService service = ServiceFactory.getInstance().getServiceType(ServiceType.FORGOTPASSWORD);

    @FXML
    private void sendOTP() {
        userEmail = emailField.getText();
        if (userEmail.isEmpty()) {
            statusLabel.setText("Enter an email!");
            return;
        }

        generatedOTP = service.generateOTP(6);
        service.sendOTP(userEmail, generatedOTP);
        statusLabel.setText("OTP sent to " + userEmail);
    }

    @FXML
    private void verifyOTP() {
        String enteredOTP = otpField.getText();
        if (enteredOTP.equals(generatedOTP)) {
            statusLabel.setText("OTP Verified! Enter new password.");
        } else {
            statusLabel.setText("Invalid OTP. Try again.");
        }
    }

    @FXML
    private void resetPassword() {
        String newPassword = newPasswordField.getText();

        if (userEmail == null || userEmail.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "User email is missing!").show();
            return;
        }

        if (newPassword == null || newPassword.isEmpty()) {
            statusLabel.setText("Enter a new password!");
            return;
        }

        if (service.resetPassword(userEmail, newPassword)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Password Reset Successful!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again!").show();
        }
    }

}
