package services.Coustom;

import services.SuperServices;

public interface ForgotPasswordService extends SuperServices {
    String generateOTP(int length);

    void sendOTP(String recipientEmail, String otp);

    boolean resetPassword(String userEmail, String newPassword);
}
