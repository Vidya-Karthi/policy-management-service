package com.policymanagement.repomanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policymanagement.model.UserDetails;

@Repository
public interface UserRepository 
        extends JpaRepository<UserDetails, String> {
 
	UserDetails findByUserId(String userId);
}