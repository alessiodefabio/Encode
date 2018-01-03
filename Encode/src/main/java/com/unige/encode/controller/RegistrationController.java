/**
 * Alessio De Fabio
 * Version: 1.0
 * Description: Controller class for the registration page, in addition to saving data, it also performs consistency checks.
 * Creation date: 11/2017
 */

package com.unige.encode.controller;

import com.unige.encode.configuration.AuthorityName;
import com.unige.encode.entity.Authority;
import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.UserInfo;
import com.unige.encode.service.AuthorityService;
import com.unige.encode.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RegistrationController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AuthorityService authorityService;

    //Registration handler, write a user's information on db if consistency checks are met..
    @RequestMapping(value = "/public/{role}/registration", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserInfo userInfo, @PathVariable String role){

        //..if exists another user with the same username, notify the client..
        if(userInfoService.findUserByUsername(userInfo.getUsername()) == null){

            //..if user is already registered, notify the client..
            if(userInfoService.findUserByEmail(userInfo.getEmail()) == null) {
                List<Authority> authorities = new ArrayList<>();

                //..checks the correct user's role format, if is wrong, notify the client,
                //else save the user's information
                if (role.equals("ROLE_USER")) {
                    authorities.add(authorityService.getAuthorityByName(AuthorityName.ROLE_USER));
                } else if (role.equals("ROLE_ADMIN")) {
                    authorities.add(authorityService.getAuthorityByName(AuthorityName.ROLE_USER));
                    authorities.add(authorityService.getAuthorityByName(AuthorityName.ROLE_ADMIN));
                } else {
                    return new ResponseEntity<>("Wrong role specified", HttpStatus.BAD_REQUEST);
                }

                userInfo.setAuthorities(authorities);
                List<Mappings> mappings = new ArrayList<>();
                userInfo.setMappings(mappings);
                userInfoService.saveUserInfo(userInfo);
            }else{
                return new ResponseEntity<>("User already registered!", HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>("User created with role: " + role, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Username already exists, please specify another username!", HttpStatus.CONFLICT);
        }
    }
}
