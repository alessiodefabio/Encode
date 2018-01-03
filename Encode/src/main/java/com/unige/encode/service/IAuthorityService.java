package com.unige.encode.service;

import com.unige.encode.configuration.AuthorityName;
import com.unige.encode.entity.Authority;
import org.springframework.stereotype.Service;


public interface IAuthorityService {

    Authority getAuthorityByName(AuthorityName name);
}
