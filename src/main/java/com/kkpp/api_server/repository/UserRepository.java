// com.kkpp.api_server.repository.UserRepository.java
package com.kkpp.api_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kkpp.api_server.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    
    boolean existsByLoginId(String loginId);

    boolean existsByName(String name);
    
    Optional<User> findByLoginIdAndDelYn(String loginId, String delYn);
}
