/**
 * 
 */
package com.policymanagement.service;

import com.policymanagement.model.LoginRequest;
import com.policymanagement.model.RegisterUser;
import com.policymanagement.model.UserResponse;

public interface UserService {

	UserResponse getUserById(LoginRequest loginRequest);

	UserResponse registerUser(RegisterUser user);
}
