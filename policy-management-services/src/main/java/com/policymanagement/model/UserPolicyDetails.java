package com.policymanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_POLICY_DETAILS")
public class UserPolicyDetails 
	{
	
	@EmbeddedId	
	private UserPolicyDetailsId userPolicyDetailsId;
	
	@Column(name = "POLICY_NAME")
	private String policyName;
	
	@Column(name = "AMOUNT_PAID")
	private Double amountPaid;
	
	@Column(name = "POLICY_END_DATE")
	private Date policyEndDate;

	public UserPolicyDetailsId getUserpolicyId() {
		return userPolicyDetailsId;
	}

	public void setUserpolicyId(UserPolicyDetailsId userpolicyId) {
		this.userPolicyDetailsId = userpolicyId;
	}

	
	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Date getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(Date policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	
}
