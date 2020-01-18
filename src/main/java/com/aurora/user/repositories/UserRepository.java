package com.aurora.user.repositories;

import com.aurora.user.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDetails,Long> {
    @Query("Select ud from UserDetails ud WHERE ud.name = ?1")
    List<UserDetails> findUserByName(String name);
}
