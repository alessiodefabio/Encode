package com.unige.encode.service;

import com.unige.encode.configuration.AuthorityName;
import com.unige.encode.entity.Authority;
import com.unige.encode.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authorityService")
public class AuthorityService implements IAuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority getAuthorityByName(AuthorityName name){
        return authorityRepository.getByName(name);
    }

}
