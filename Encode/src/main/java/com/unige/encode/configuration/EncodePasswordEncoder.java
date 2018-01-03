/**
 * Alessio De Fabio
 * Version: 1.0
 * Description: A Spring Security framework class that encrypts the user's password
 * with a view to greater security.
 * Creation date: 11/2017
 */

package com.unige.encode.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class EncodePasswordEncoder extends WebMvcConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}
