/**
 * Alessio De Fabio
 * Version: 1.0
 * Description: Controller class for user information.
 * Creation date: 12/2017
 */

package com.unige.encode.controller;

import com.unige.encode.configuration.RestHandler;
import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.TopicMap;
import com.unige.encode.entity.UserInfo;
import com.unige.encode.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends RestHandler {

    @Autowired
    private UserInfoService userInfoService;

    //Delete user with username specify on the request..
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable String username){

        //..if user exist..
        if(userInfoService.findUserByUsername(username) != null){
            userInfoService.deleteUserInfoByUsername(username);
            return new ResponseEntity<>("User with username: " + username + " correctly deleted", HttpStatus.OK);
        }
        //..else return bad_request http status.
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }

    //Request that return all user's topicmaps..
    @RequestMapping(value = "topicmaps/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAllTopicMapByUserId(@PathVariable int user_id, WebRequest request, RuntimeException ex){

        //..if user exists extract all its mapping..
            if(userInfoService.findUserById(user_id) != null){
            List<Mappings> mappings = userInfoService.getMappingOfUser(user_id);

            //..if the mapping list isn't empty then return all topicmap on it,
            //else notify a not found http status.
            if(!mappings.isEmpty()){
                List<TopicMap> allTopicMaps = null;
                for(Mappings map: mappings){
                    allTopicMaps.add(map.getMappedTopic());
                }
                return new ResponseEntity<>(allTopicMaps, HttpStatus.OK);
            }
            return new ResponseEntity<>("Any Topic Map found!", HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(handleResourceNotFoundEx(ex, request), HttpStatus.NOT_FOUND);
        }
    }

    //Update user information..
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserInfo(@RequestBody UserInfo userInfo){
        UserInfo usr = userInfoService.findUserByUsername(userInfo.getUsername());

        //..if user exist update it..
        if(usr != null){
            userInfoService.updateUserInfo(usr);
            return new ResponseEntity<>("User " + userInfo.getUsername() + " correctly updated!", HttpStatus.OK);
        }else{
            //..otherwise notify a not found http status.
            return new ResponseEntity<>("User " + userInfo.getUsername() + " not exists!", HttpStatus.NOT_FOUND);
        }
    }
}
