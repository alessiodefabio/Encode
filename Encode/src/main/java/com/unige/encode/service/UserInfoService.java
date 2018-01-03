/**
 * Alessio De Fabio
 * Version: 1.0
 * Description: UserInfo service class, the DAO is implemented in it.
 * Creation date: 11/2017
 */

package com.unige.encode.service;

        import com.unige.encode.configuration.EncodePasswordEncoder;
        import com.unige.encode.configuration.JwtUserFactory;
        import com.unige.encode.entity.Mappings;
        import com.unige.encode.entity.UserInfo;
        import com.unige.encode.repository.UserInfoRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsServiceConfigurer;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.stereotype.Service;

        import java.sql.Date;
        import java.sql.Timestamp;
        import java.util.List;

@Service("userInfoService")
public class UserInfoService implements IUserInfoService, UserDetailsService {

    @Autowired
    //private BCryptPasswordEncoder passwordEncoder;
    private EncodePasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("userInfoRepository")
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }

    @Override
    public void saveUserInfo(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.passwordEncoder().encode(userInfo.getPassword()));
        System.out.print(userInfo.getPassword());
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo findUserByEmail(String email){
        return userInfoRepository.findByEmail(email);
    }

    @Override
    public UserInfo findUserByUsername(String username){
        return userInfoRepository.findByUsername(username);
    }

    @Override
    public UserInfo findUserById(int id){return userInfoRepository.findUserInfoById(id);}

    @Override
    public List<UserInfo> getAllUserByIsEnabled(boolean enabled){return userInfoRepository.getAllByEnabled(enabled);}

    @Override
    public List<Mappings> getMappingOfUser(int user_id) {
        UserInfo usr = findUserById(user_id);
        return usr.getMappings();
    }

    @Override
    public void deleteUserInfoByUsername(String username) {
        userInfoRepository.deleteByUsername(username);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo){
        UserInfo usr = findUserByUsername(userInfo.getUsername());
        usr.setPassword(userInfo.getPassword());
        usr.setEmail(userInfo.getEmail());
        usr.setFirstname(userInfo.getFirstname());
        usr.setLastname(userInfo.getLastname());
        usr.setEnabled(userInfo.isEnabled());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        usr.setLastPasswordResetDate(timestamp);
    }
}
