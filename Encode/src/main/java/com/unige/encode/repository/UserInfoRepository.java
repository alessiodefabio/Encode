/**
 * Alessio De Fabio
 * Version: 1.0
 * Description: Repository for UserInfo class.
 * Creation date: 11/2017
 */

/* Only for test purposes, MODIFY!!!*/

package com.unige.encode.repository;

import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.TopicMap;
import com.unige.encode.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("userInfoRepository")
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByEmail(String email);
    UserInfo findByUsername(String username);
    UserInfo findUserInfoById(int id);
    List<UserInfo> getAllByEnabled(boolean enabled);
    UserInfo findById(int user_id);
    void deleteByUsername(String username);
}
