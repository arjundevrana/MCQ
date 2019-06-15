package haj.controller;

import haj.model.User;
import haj.repository.UserRepository;
import haj.service.EmailService;
import haj.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class OtpController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public OtpService otpService;
    @Autowired
    public EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/generateOtp")
    public Map<String,String> generateOtp(@RequestBody Map<String,String> payLoad){
        Map<String,String> response = new HashMap<>();
        String mobileNumber = payLoad.get("mobileNumber");
        User user = userRepository.findByMobileNumber(mobileNumber);
        if(user!=null&&mobileNumber !=null)
        {
            if(mobileNumber.equalsIgnoreCase(user.getMobileNumber())){
                int otp = otpService.generateOTP(mobileNumber);
                response.put("otp",String.valueOf(otp));
                response.put("status","200");
                response.put("message","Success");
                logger.info("OTP : "+otp);
            }
        } else{
            response.put("status","400");
            response.put("message","Mobile number not found");
        }
        return response;
    }

    @PostMapping(value ="/validateOtp")
    public @ResponseBody Map<String,String> validateOtp(@RequestBody Map<String,String> payLoad){
        Map<String,String> response = new HashMap<>();
        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";
        String mobileNumber = payLoad.get("mobileNumber");

        logger.info(" Otp Number : "+payLoad.get("otp"));
        int otp = Integer.parseInt(payLoad.get("otp"));
        if(otp >= 0){
            int serverOtp = otpService.getOtp(payLoad.get("mobileNumber"));
            if(serverOtp > 0){
                if(otp == serverOtp){
                    otpService.clearOTP(mobileNumber);
                    response.put("status","200");
                    response.put("message","Entered Otp is valid");
                    return response;
                }else{
                    response.put("status","200");
                    response.put("message",FAIL);
                    return response;
                }
            }else {
                response.put("status","400");
                response.put("message",FAIL);
                return response;
            }
        }else {
            response.put("status","400");
            response.put("message",FAIL);
            return response;
        }
    }
}
