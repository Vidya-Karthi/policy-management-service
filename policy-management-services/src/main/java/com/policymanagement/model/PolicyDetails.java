package com.policymanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "POLICY_DETAILS")
public class PolicyDetails {
	
	@Id
	@javax.persistence.Id
	@Column(name = "POLICY_NO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policyNo;

	@Column(name = "POLICY_NAME")
	private String policyName;
	
	@Column(name = "POLICY_DETAILS")
	private String policyDetails;

	public int getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyDetails() {
		return policyDetails;
	}

	public void setPolicyDetails(String policyDetails) {
		this.policyDetails = policyDetails;
	}
}
