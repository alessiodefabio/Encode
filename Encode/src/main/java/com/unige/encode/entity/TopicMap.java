package com.unige.encode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "topicmaps")
public class TopicMap implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_map_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "version")
    private String version;

    @OneToOne(mappedBy = "mappedTopic", targetEntity = Mappings.class, cascade = CascadeType.ALL)
    @JsonIgnore
    private Mappings mappings;
/*
    @OneToMany(mappedBy = "topicMap", targetEntity = Topic.class, cascade = CascadeType.ALL)
    private Set<Topic> owningTopic;
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public Mappings getMappings() {
        return mappings;
    }

    public void setMappings(Mappings mappings) {
        this.mappings = mappings;
    }
/*
    public Set<Topic> getOwningTopic() {
        return owningTopic;
    }

    public void setOwningTopic(Set<Topic> owningTopic) {
        this.owningTopic = owningTopic;
    }
    */
}
