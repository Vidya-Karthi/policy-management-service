/**
 * 
 */
package com.policymanagement.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.policymanagement.model.PolicyDetails;
import com.policymanagement.model.PolicyDetailsResponse;
import com.policymanagement.model.UserPolicyDetails;
import com.policymanagement.model.UserPolicyDetailsId;
import com.policymanagement.model.UserPolicyResponse;
import com.policymanagement.repomanager.PolicyRepository;
import com.policymanagement.repomanager.PolicyUpdRepository;
import com.policymanagement.repomanager.UserPolicyRepository;
import com.policymanagement.service.PolicyService;




@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private UserPolicyRepository userPolicyRepository;
	
	@Autowired
	private PolicyUpdRepository policyupd;

	public List<PolicyDetailsResponse> getPolicyDetails() {
		List<PolicyDetails> policyDetails =	policyRepository.findAll();
		List<PolicyDetailsResponse> policyRes = buildPolicyResponse(policyDetails);
		return policyRes;
	}

	public List<PolicyDetailsResponse> buildPolicyResponse(List<PolicyDetails> policyDetails) {
		List<PolicyDetailsResponse> newpolicyRes = new ArrayList<>();
		if (!CollectionUtils.isEmpty(policyDetails)) {
			for (PolicyDetails policy : policyDetails) {
				PolicyDetailsResponse policyRes = new PolicyDetailsResponse();
				String policyId = Integer.toString(policy.getPolicyNo());
				policyRes.setPolicyId(policyId);
				policyRes.setPolicyName(policy.getPolicyName());
				policyRes.setPolicyDetails(policy.getPolicyDetails());
				newpolicyRes.add(policyRes);
			}
		}
		return newpolicyRes;
	}

	@Override
	public List<UserPolicyResponse> getUserPolicyDetails(String userId) {
		List<UserPolicyDetails> userPolicyDetails = userPolicyRepository.findByUserPolicyDetailsIdUserId(userId);
		List<UserPolicyResponse> userPolicyRes = buildUserPolicyResponse(userPolicyDetails);
		return userPolicyRes;
	}
	
	public List<UserPolicyResponse> buildUserPolicyResponse(List<UserPolicyDetails> userPolicyDetails) {
		List<UserPolicyResponse> newuserPolicyRes = new ArrayList<>();
		if (!CollectionUtils.isEmpty(userPolicyDetails)) {
			for (UserPolicyDetails userPolicy : userPolicyDetails) {
				UserPolicyResponse userPolicyRes = new UserPolicyResponse();
				UserPolicyDetailsId userPolicyDetailsId = userPolicy.getUserpolicyId();
				userPolicyRes.setUserId(userPolicyDetailsId.getUserId());
				String policyNo = Integer.toString(userPolicyDetailsId.getPolicyNo());
				userPolicyRes.setPolicyNo(policyNo);
				userPolicyRes.setPolicyName(userPolicy.getPolicyName());
				String amountPaid = Double.toString(userPolicy.getAmountPaid());
				userPolicyRes.setAmountPaid(amountPaid);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
				Date date = new Date();
				String endDate = dateFormat.format(userPolicy.getPolicyEndDate());
				userPolicyRes.setPolicyEndDate(endDate);
				if (userPolicy.getPolicyEndDate().compareTo(date) >= 0) {
					userPolicyRes.setValid("Yes");
				}
				else {
					userPolicyRes.setValid("No");
				}
				
				newuserPolicyRes.add(userPolicyRes);
			}
		}
		return newuserPolicyRes;
	}

	@Override
	public List<PolicyDetailsResponse> savePolicyDetails(List<PolicyDetailsResponse> updPolicyDetails) {
		// TODO Auto-generated method stub
		List<PolicyDetails> newpolicyRes = new ArrayList<>();
		PolicyDetails policy = new PolicyDetails();
		List<PolicyDetails> updpolicyList =  new ArrayList<>();
		System.out.println("iterating policy update");
		for (PolicyDetailsResponse updPolicy : updPolicyDetails) {
			policy.setPolicyNo(Integer.parseInt(updPolicy.getPolicyId()));
			policy.setPolicyName(updPolicy.getPolicyName());
			policy.setPolicyDetails(updPolicy.getPolicyDetails());
			PolicyDetails updpolicyRes = policyupd.save(policy);
			updpolicyList.add(updpolicyRes);
			System.out.println("iterating policy update" + updpolicyList.toString());
		}
		List<PolicyDetails> policyDetails =	policyRepository.findAll();
		List<PolicyDetailsResponse> policyRes = buildPolicyResponse(policyDetails);
		return policyRes;
	}
}
