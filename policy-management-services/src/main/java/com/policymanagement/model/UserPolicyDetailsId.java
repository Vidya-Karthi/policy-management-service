package com.policymanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserPolicyDetailsId 
	implements Serializable {
		private static final long serialVersionUID = 1L;
	
	@Column(name = "POLICY_NO")
	private int policyNo;
	
	@Column(name = "USER_ID")
	private String userId;

	
	public UserPolicyDetailsId() {

    }
	 public UserPolicyDetailsId(int policyNo, String userId) {
	        this.policyNo = policyNo;
	        this.userId = userId;
	    }
	 
		public int getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

		
}
