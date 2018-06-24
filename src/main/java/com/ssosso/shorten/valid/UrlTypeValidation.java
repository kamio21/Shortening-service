package com.ssosso.shorten.valid;

import java.net.URL;
import java.net.URLConnection;

public class UrlTypeValidation implements Validation {

    @Override
    public boolean valid(String url) {
        try {
            URL connectionUrl = new URL(url);
            URLConnection conn = connectionUrl.openConnection();
            conn.connect();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
