package com.unige.encode.repository;

import com.unige.encode.configuration.AuthorityName;
import com.unige.encode.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("authorityRepository")
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority getByName(AuthorityName name);
}
