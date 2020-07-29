/**
 * 
 */
package com.policymanagement.service;

import java.util.List;

import com.policymanagement.model.PolicyDetailsResponse;
import com.policymanagement.model.UserPolicyResponse;

public interface PolicyService {

	List<PolicyDetailsResponse> getPolicyDetails();
	
	List<UserPolicyResponse> getUserPolicyDetails(String userId);

	List<PolicyDetailsResponse> savePolicyDetails(List<PolicyDetailsResponse> updPolicyDetails);
}
