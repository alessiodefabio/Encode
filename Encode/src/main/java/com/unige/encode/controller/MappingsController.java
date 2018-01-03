/**
 * Alessio De Fabio
 * Version: 1.0
 * Description: Controller class for the user's mappings, save the user-topicmap relationship,
 * the creation date and the last modify date.
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
@RequestMapping(value = "/mappings")
public class MappingsController {

    @Autowired
    private MappingsService mappingsService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TopicMapService topicMapService;

    //At mapping request, save mapping info if consistency checks are met..
    @RequestMapping(value = "save/{username}/{topic_map_id}", method = RequestMethod.POST)
    public ResponseEntity<?> addMapping(@PathVariable String username, @PathVariable int topic_map_id){

        //..find the request user..
        TopicMap topicMap = topicMapService.findTopicMapById(topic_map_id);
        if(topicMap != null ){

            //..if user exist, find the topic map to map, if exist ..
            UserInfo userInfo = userInfoService.findUserByUsername(username);
            if(userInfo != null){
                Mappings mapping = new Mappings();
                mapping.setMappedTopic(topicMap);
                mapping.setUserMaps(userInfo);
                List<Mappings> mappingsList = userInfo.getMappings();
                mappingsList.add(mapping);
                mappingsService.saveMappings(mapping);
                return new ResponseEntity<>("Mapping correctly saved", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Username not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("TopicMap not found", HttpStatus.BAD_REQUEST);
    }

    //Delete mapping between topic map and user..
    @RequestMapping(value = "/{mapping_id}", method = RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<?> deleteMapping(@PathVariable int mapping_id){

        //..if mapping exist..delete
        if(mappingsService.findMappingsById(mapping_id) != null){
            mappingsService.deleteMappingsById(mapping_id);
            return new ResponseEntity<>("Mapping with id: " + mapping_id +" deleted", HttpStatus.OK);
        }else{
            //..else notify not found status.
            return new ResponseEntity<>("Mapping with id: " + mapping_id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    //Retrieve user's mapping between itself and topic map..
    @RequestMapping(value = "/user/{user_id}/", method = RequestMethod.GET)
    public ResponseEntity<?> getMappingsByUserId(@PathVariable int user_id){
        //..extract all user's mapping..
        List<Mappings> allUserMappings = mappingsService.findAllMappingByUserId(user_id);

        //..if list is empty notify it to user..
        if(allUserMappings.isEmpty()){
            return new ResponseEntity<>("There's no mapping for this user", HttpStatus.OK);
        }else{
            //..else return list.
            return new ResponseEntity<>(allUserMappings, HttpStatus.OK);
        }
    }

    //Retrive mapping of a specific topic map. Return a list in case of co-editor
    @RequestMapping(value = "/topicmaps/{topic_map_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMappingByTopicMapId(@PathVariable int topic_map_id){
        List<Mappings> allTopicMappings = mappingsService.findAllByTopicMapId(topic_map_id);

        //..if list is empty notify it to user..
        if(allTopicMappings.isEmpty()){
            return new ResponseEntity<>("There's no mapping for this topic map", HttpStatus.OK);
        }else{
            //..else return list.
            return new ResponseEntity<>(allTopicMappings, HttpStatus.OK);
        }
    }
}
