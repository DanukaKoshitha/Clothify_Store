package services.Coustom.Impl;

import Repository.Custom.ForgotPasswodDao;
import Repository.DaoFactory;
import Util.DaoType;
import services.Coustom.ForgotPasswordService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Properties;

public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private static final String DIGITS = "0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    private static ForgotPasswordServiceImpl instance;
    private ForgotPasswordServiceImpl(){}

    public static ForgotPasswordServiceImpl getInstance(){
        return instance == null ? instance = new ForgotPasswordServiceImpl() : instance;
    }

    ForgotPasswodDao daoForgotPassword = DaoFactory.getInstance().getDaoType(DaoType.FORGOTPASSWORD);

    @Override
    public String generateOTP(int length) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }
        return otp.toString();
    }

    private static final String EMAIL = "danukakoshitha2004@gmail.com";
    private static final String PASSWORD = "xdqf ierm pahc xqxb"; // Use App Password

    @Override
    public void sendOTP(String recipientEmail, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Password Reset OTP");
            message.setText("Your OTP code for password reset is: " + otp);

            Transport.send(message);
            System.out.println("OTP sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean resetPassword(String userEmail, String newPassword) {
        return daoForgotPassword.resetPassword(userEmail,newPassword);
    }
}
