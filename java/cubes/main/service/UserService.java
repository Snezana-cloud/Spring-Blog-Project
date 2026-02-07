//package cubes.main.service;
//
//import java.util.Locale;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.stereotype.Repository;
//
//import cubes.main.entity.ChangePassword;
//import cubes.main.entity.Users;
//import jakarta.servlet.http.HttpServletRequest;
//
//public interface UserService {
//
//	public SimpleMailMessage optEmaiMailMessage(Users user, int otp);
//	public String sendResetEmail(String email, HttpServletRequest request);
//	public SimpleMailMessage constructEmail(String appUrl, Locale locale, String token, Users user);
//	public String updatePassword(ChangePassword changePassword);
//}
