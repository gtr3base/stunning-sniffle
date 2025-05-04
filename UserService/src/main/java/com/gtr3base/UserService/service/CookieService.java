package com.gtr3base.UserService.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author hypad on 01.05.2025
 * @project SMedia
 */
@Slf4j
@Service
public class CookieService {
    public void createCookie(Map<String, String> map, HttpServletResponse response) {
        for(Map.Entry<String, String> en: map.entrySet()){
            if(en.getValue() != null){
                Cookie cookie = new Cookie(en.getKey(), en.getValue());
                cookie.setMaxAge(6000);
                response.addCookie(cookie);
                log.info("Cookie: {}", cookie);
            }
            else{
                log.info("Map is null");
            }
        }
    }
}
