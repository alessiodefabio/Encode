package com.unige.encode.service;

import com.unige.encode.entity.TopicMap;
import com.unige.encode.repository.TopicMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("topicMapService")
public class TopicMapService implements ITopicMapService{

    @Autowired
    private TopicMapRepository topicMapRepository;

    @Override
    public TopicMap findTopicMapById(int id) {
        return topicMapRepository.findById(id);
    }

    @Override
    public void saveTopicMap(TopicMap topicMap) {
        topicMapRepository.save(topicMap);
    }

    @Override
    public void updateTopicMap(TopicMap topicMap) {
        TopicMap tpcMap = topicMapRepository.findById(topicMap.getId());

        tpcMap.setDescription(topicMap.getDescription());
        tpcMap.setTitle(topicMap.getTitle());
        tpcMap.setVersion(topicMap.getVersion());
    }

    @Override
    public void deleteTopicMapById(int id) {
        topicMapRepository.deleteById(id);
    }

    @Override
    public TopicMap findTopicMapByTitle(String title) {
        return topicMapRepository.findByTitle(title);
    }
}
