package com.policymanagement.repomanager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policymanagement.model.UserPolicyDetails;
import com.policymanagement.model.UserPolicyDetailsId;

@Repository
public interface UserPolicyRepository 
        extends JpaRepository<UserPolicyDetails, UserPolicyDetailsId>{
 
	List<UserPolicyDetails> findByUserPolicyDetailsIdUserId(String userId);
	
} 