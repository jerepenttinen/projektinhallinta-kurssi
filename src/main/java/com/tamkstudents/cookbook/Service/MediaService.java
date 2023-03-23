package com.tamkstudents.cookbook.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class MediaService {
    public String imgToBase64(byte[] img){
        return Base64Utils.encodeToString(img);
    }
}
