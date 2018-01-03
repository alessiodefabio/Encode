/**
 * Alessio De Fabio
 * Version: 1.0
 * Description: Rest Controller class for topic map information.
 * Creation date: 12/2017
 */

package com.unige.encode.controller;

import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.TopicMap;
import com.unige.encode.entity.UserInfo;
import com.unige.encode.service.MappingsService;
import com.unige.encode.service.TopicMapService;
import com.unige.encode.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topicmaps")
public class TopicMapController {

    @Autowired
    private TopicMapService topicMapService;

    @Autowired
    private UserInfoService userInfoService;

    //Search for a specific topic map by id..
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getTopicMap(@PathVariable int id){
        TopicMap topicMap = topicMapService.findTopicMapById(id);

        //..if the searched topic map exist, return it..
        if(topicMap != null){
            return new ResponseEntity<>(topicMap, HttpStatus.OK);
        }else{
            //..else notify a not found http status.
            return new ResponseEntity<>("TopicMap not found", HttpStatus.NOT_FOUND);
        }

    }


    //Post request for store a new user's topic map..
    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public ResponseEntity<?> addTopicMap(@RequestBody TopicMap topicMap,@PathVariable String username){

        //..extract user's mapping..
        UserInfo userInfo = userInfoService.findUserByUsername(username);
        List<Mappings> mappings = userInfo.getMappings();

        //..if already exist a topic map with the same name as the new one notify a conflict status..
        for(Mappings map: mappings){
            if(map.getMappedTopic().getTitle().equals(topicMap.getTitle()))
                return new ResponseEntity<>("Topicmap already exist!", HttpStatus.CONFLICT);
            }

        //..else save the map.
        topicMapService.saveTopicMap(topicMap);
        return new ResponseEntity<> ("redirect:/mappings/save" + username +"/"+topicMap.getId(), HttpStatus.CREATED);

    }

    //Put request for update an already exist topic map..
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json",
    produces = "application/json")
    public ResponseEntity<?> updateTopicMap(@PathVariable int id, @RequestBody TopicMap topicMap){
        if(topicMapService.findTopicMapById(id) != null){
            topicMapService.updateTopicMap(topicMap);
            return new ResponseEntity<>("TopicMap correctly updated!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("TopicMap does not exist, do you want create now?", HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<?> deleteTopicMap(@PathVariable int id){
        if(topicMapService.findTopicMapById(id) != null){
            topicMapService.deleteTopicMapById(id);
            return new ResponseEntity<>("Redirect:/mappings/"+id, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Specify an exists topic map!", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getTopicMapByTitle(@PathVariable String title){
        TopicMap topicMap = topicMapService.findTopicMapByTitle(title);
        if(topicMap != null){
            return new ResponseEntity<>(topicMap, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Topic Map not found!", HttpStatus.NO_CONTENT);
        }
    }
}
