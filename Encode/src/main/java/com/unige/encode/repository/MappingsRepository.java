package com.unige.encode.repository;

import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.TopicMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mappingsRepository")
public interface MappingsRepository extends JpaRepository<Mappings, Long> {

    Mappings findById(int id);
    void deleteById(int id);
    List<Mappings> findAllByUserMaps_Id(int user_id);
    List<Mappings> findAllByMappedTopic_Id(int topic_map_id);
}
