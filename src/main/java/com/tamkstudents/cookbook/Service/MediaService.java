package com.tamkstudents.cookbook.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class MediaService {
    public String imageToBase64(byte[] image) {
        return Base64Utils.encodeToString(image);
    }

    public byte[] base64ToImage(String base64) {
        return Base64Utils.decodeFromString(base64);
    }
}
