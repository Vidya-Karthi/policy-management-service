package com.policymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class UserPolicyRequest {

		/** The id. */
		private String userId;


		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}
}