package com.oceanview.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

/**
 * Utility class for sending emails
 * Used for forgot password functionality
 */
public class EmailUtil {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USERNAME = "your-email@gmail.com"; // Configure this
    private static final String SMTP_PASSWORD = "your-app-password"; // Configure this

    /**
     * Send password reset email
     * @param toEmail Recipient email
     * @param newPassword New password
     * @return true if email sent successfully
     */
    public static boolean sendPasswordResetEmail(String toEmail, String newPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Ocean View Resort - Password Reset");

            String emailContent = "Dear User,\n\n"
                    + "Your password has been reset successfully.\n\n"
                    + "New Password: " + newPassword + "\n\n"
                    + "Please login with this new password and change it immediately.\n\n"
                    + "Best Regards,\n"
                    + "Ocean View Resort Team";

            message.setText(emailContent);

            Transport.send(message);
            System.out.println("Password reset email sent to: " + toEmail);
            return true;

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
            return false;
        }
    }

    /**
     * Generate random password
     * @return Random password meeting validation requirements
     */
    public static String generateRandomPassword() {
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String special = "!@#$%^&*";
        
        String allChars = uppercase + lowercase + numbers + special;
        StringBuilder password = new StringBuilder();
        
        // Ensure at least one character from each category
        password.append(uppercase.charAt((int) (Math.random() * uppercase.length())));
        password.append(lowercase.charAt((int) (Math.random() * lowercase.length())));
        password.append(numbers.charAt((int) (Math.random() * numbers.length())));
        password.append(special.charAt((int) (Math.random() * special.length())));
        
        // Add remaining characters
        for (int i = 4; i < 12; i++) {
            password.append(allChars.charAt((int) (Math.random() * allChars.length())));
        }
        
        return password.toString();
    }
}
