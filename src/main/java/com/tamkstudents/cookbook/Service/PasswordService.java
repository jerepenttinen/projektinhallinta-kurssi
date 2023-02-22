package com.tamkstudents.cookbook.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class PasswordService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);

    public String encrypt(String rawPassword){
        return encoder.encode(rawPassword);
    }

    public Boolean comparePassword(CharSequence rawPassWord, String encodedPassword){
        return  encoder.matches(rawPassWord, encodedPassword);
    }

    public Boolean validatePassword(CharSequence rawPassword){
        if(!rawPassword.isEmpty()
                && rawPassword.length() > 7
                && rawPassword.length() < 32
        ){
            return false;
        }else{
            return true;
        }
    }
}
