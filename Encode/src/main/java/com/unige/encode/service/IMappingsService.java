package com.unige.encode.service;

import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.TopicMap;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IMappingsService {

    void saveMappings(Mappings mappings);
    Mappings findMappingsById(int id);
    void deleteMappingsById(int id);
    List<Mappings> findAllMappingByUserId(int user_id);
    List<Mappings> findAllByTopicMapId(int topic_map_id);
}
