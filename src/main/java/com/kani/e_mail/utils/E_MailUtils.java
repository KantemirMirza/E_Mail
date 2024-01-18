package com.kani.e_mail.utils;

public class E_MailUtils {
    public static String getEmailMessage(String name, String host, String token){
        return "Hello " + name + ",\n\n Your Account Has Been Created. Please click the Link bellow to Verify your account \n\n"
                + verificationUrl(host, token);
    }
    public static String verificationUrl(String host, String token) {
        return host + "/api/users?token=" + token + "\n\n the support team";
    }
}
