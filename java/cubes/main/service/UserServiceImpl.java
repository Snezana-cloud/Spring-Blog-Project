//package cubes.main.service;
//
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import cubes.main.dao.UserRepository;
//import cubes.main.entity.ChangePassword;
//import cubes.main.entity.Users;
//import jakarta.servlet.http.HttpServletRequest;
//
//
//import java.util.Locale;
//import java.util.ResourceBundle;
//import java.util.UUID;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Value("${spring.mail.username}")
//    private String fromEmail;
//
//    private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
//
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    
//  
//	@Override
//	public SimpleMailMessage optEmaiMailMessage(Users user, int otp) {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(fromEmail);
//        msg.setTo(user.getEmail());
//
//        msg.setSubject("Log in to your account");
//        msg.setText("Please enter the following verification code to verify this login attempt." + "\n\n" + otp + "\n\n" + "Regards \n" + "Vikas Kumar");
//        return msg;
//    }
//	
// 
//	@Override
//	public String sendResetEmail(String email,HttpServletRequest  request) {
//		   try {
//               Users user = userRepository.findByPasswordToken(email);
//               if (user == null) {
//                   throw new UsernameNotFoundException("USER NOT FOUND");
//               }
//               String token = UUID.randomUUID().toString();
//               user.setEmail(email);
//               user.setResetToken(token);
//               userRepository.save(user);
//               javaMailSender.send(constructEmail(getAppUrl(request), request.getLocale(), token, user));
//               return "SUCCESS";
//           } catch (Exception e) {
//               e.printStackTrace();
//               return null;
//           }
//
//	}
//  
//	@Override
//	public SimpleMailMessage constructEmail(String appUrl, Locale locale, String token, Users user) {
//        String url = appUrl + "/forgot/changePwd?token=" + token;
//        String message = resourceBundle.getString("message.resetPassword");
//        return constructEmail("Reset Password", message + " \r\n" + url, user);
//	}
//
//    private String getAppUrl(HttpServletRequest request) {
//        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//    }
//    
//    private SimpleMailMessage constructEmail(String subject, String body,Users user) {
//    	SimpleMailMessage email = new SimpleMailMessage();
//    	email.setSubject(subject);
//    	email.setText(body);
//    	email.setTo(user.getEmail());
//    	email.setFrom(fromEmail);
//    	return email;
//}
//
//	@Override
//	public String updatePassword(ChangePassword changePassword) {
//        Users user = userRepository.findByPasswordToken(changePassword.getToken());
//        if (user != null) {
//            user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
//            userRepository.save(user);
//            return "SUCCESS";
//        }
//        return null;
//	}
//}


