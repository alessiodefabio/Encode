package com.unige.encode.repository;

import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.TopicMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("topicMapRepository")
public interface TopicMapRepository extends JpaRepository<TopicMap, Long> {

    TopicMap findById(int id);
    void deleteById(int id);
    TopicMap findByTitle(String title);

}
