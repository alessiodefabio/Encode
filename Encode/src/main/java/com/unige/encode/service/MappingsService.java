package com.unige.encode.service;

import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.TopicMap;
import com.unige.encode.repository.MappingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mappingService")
public class MappingsService implements IMappingsService {

    @Autowired
    MappingsRepository mappingsRepository;

    @Override
    public void saveMappings(Mappings mappings) {
        mappingsRepository.save(mappings);
    }

    @Override
    public Mappings findMappingsById(int id) {
        return mappingsRepository.findById(id);
    }

    @Override
    public void deleteMappingsById(int id) {
        mappingsRepository.deleteById(id);
    }

    @Override
    public List<Mappings> findAllMappingByUserId(int user_id) {
        return mappingsRepository.findAllByUserMaps_Id(user_id);
    }

    @Override
    public List<Mappings> findAllByTopicMapId(int topic_map_id) {
        return mappingsRepository.findAllByMappedTopic_Id(topic_map_id);
    }
}
