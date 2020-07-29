package com.policymanagement.repomanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policymanagement.model.PolicyDetails;

@Repository
public interface PolicyUpdRepository 
        extends JpaRepository<PolicyDetails, Long> {
 
	
	@SuppressWarnings("unchecked")
	PolicyDetails save(PolicyDetails updPolicy);
}

