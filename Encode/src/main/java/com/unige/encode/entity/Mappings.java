/**
 * Alessio De Fabio
 * MySQL Table: mapping
 * Version: 1.0
 * Description: A class that represent the mapping between User's and its Topic Map.
 * Creation date: 11/2017
 */

package com.unige.encode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "mappings")
public class Mappings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "mapping_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private UserInfo userMaps;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_map_id", referencedColumnName = "topic_map_id")
    @JsonIgnore
    private TopicMap mappedTopic;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "last_modify_date")
    private Timestamp lastModifyDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public TopicMap getMappedTopic() {
        return mappedTopic;
    }

    public void setMappedTopic(TopicMap topicMap) {
        this.mappedTopic = topicMap;
    }


    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }


    public Timestamp getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Timestamp lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }


    public UserInfo getUserMaps() {
        return userMaps;
    }

    public void setUserMaps(UserInfo userMaps) {
        this.userMaps = userMaps;
    }

}
