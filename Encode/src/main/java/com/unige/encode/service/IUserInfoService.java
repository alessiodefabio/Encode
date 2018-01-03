/**
 * Alessio De Fabio
 * Version: 1.0
 * Description: Interface for UserInfo service class.
 * Creation date: 11/2017
 */

package com.unige.encode.service;

import com.unige.encode.entity.Mappings;
import com.unige.encode.entity.UserInfo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Set;


public interface IUserInfoService {
    void saveUserInfo(UserInfo userInfo);
    UserInfo findUserByEmail(String email);
    UserInfo findUserByUsername(String username);
    List<UserInfo> getAllUserByIsEnabled(boolean isActive);
    List<Mappings> getMappingOfUser(int user_id);
    UserInfo findUserById(int user_id);
    void deleteUserInfoByUsername(String username);
    void updateUserInfo(UserInfo userInfo);
}
