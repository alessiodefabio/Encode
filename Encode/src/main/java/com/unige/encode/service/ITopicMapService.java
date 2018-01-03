package com.unige.encode.service;

import com.unige.encode.entity.TopicMap;

public interface ITopicMapService {

    TopicMap findTopicMapById(int id);
    void saveTopicMap(TopicMap topicMap);
    void updateTopicMap(TopicMap topicMap);
    void deleteTopicMapById(int id);
    TopicMap findTopicMapByTitle(String title);
}
