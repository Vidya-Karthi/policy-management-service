package com.policymanagement.repomanager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policymanagement.model.PolicyDetails;


@Repository
public interface PolicyRepository 
        extends JpaRepository<PolicyDetails, Long> {
 
	List<PolicyDetails> findAll();
	
}