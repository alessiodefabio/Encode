/**
 * Alessio De Fabio
 * MySQL Table: users
 * Version: 1.0
 * Description: A class that represent an Encode's users.
 * Creation date: 11/2017
 */

package com.unige.encode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

//Representing a user in encode
@Entity
@Table(name = "usersinfo")
public class UserInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name="username")
    //@NotEmpty(message = "*Please provide an username")
    private String username;

    @Column(name = "password")
    //@Length(min = 5, message = "*Your password must have at least 5 characters")
    //@NotEmpty(message = "*Please provide your password")
    //@Transient
    private String password;

    @Column(name = "email")
    //@Email(message = "*Please provide a valid Email")
    //@NotEmpty(message = "*Please provide an email")
    private String email;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reg_date")
    private Timestamp regDate;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "last_modify_password")
    private Timestamp lastPasswordResetDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "authority_id")})
    private List<Authority> authorities;

    //Representation of the relationships between the various entities in the db.

/*
    @OneToMany(mappedBy = "ownerUser",targetEntity = TopicType.class, cascade = CascadeType.ALL)
    private Set<TopicType> userTopic;
*/
    @OneToMany(mappedBy = "userMaps",targetEntity = Mappings.class, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Mappings> mappings;


    //Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

   /*
    public Set<TopicType> getUserTopic() {
        return userTopic;
    }

    public void setUserTopic(Set<TopicType> userTopic) {
        this.userTopic = userTopic;
    }
*/
    public List<Mappings> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mappings> mappings) {
        this.mappings = mappings;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}

