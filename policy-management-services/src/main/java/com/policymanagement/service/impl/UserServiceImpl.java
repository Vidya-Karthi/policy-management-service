/**
 * 
 */
package com.policymanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanagement.model.LoginRequest;
import com.policymanagement.model.RegisterUser;
import com.policymanagement.model.UserDetails;
import com.policymanagement.model.UserResponse;
import com.policymanagement.repomanager.UserRepository;
import com.policymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserResponse getUserById(LoginRequest loginRequest) {
		UserResponse userRes = new UserResponse();
		String userId = loginRequest.getUserId();
		String password = loginRequest.getPassword();
		UserDetails userDetails = userRepository.findByUserId(userId);
		//System.out.println("query op" + userDetails.toString());
		if (userDetails != null) {
			userRes = buildUserResponse(userDetails, password);
		}
		else {
			userRes.setUserId(userId);
			userRes.setUserStatus("NewUser");
			userRes.setResponseStatus("Failure");
			userRes.setRole(null);
		}
		return userRes;
	}

	public UserResponse registerUser(RegisterUser user)  {
		String userId = user.getUserId();
		UserDetails userDetails = userRepository.findByUserId(userId);
		UserResponse userRes = new UserResponse();
		if (userDetails != null) {
			userRes.setUserId(userId);
			userRes.setUserStatus("ExistingUser");
			userRes.setResponseStatus("Success");
			userRes.setRole(userDetails.getRole());
			return userRes;
		} else {
			UserDetails newUser = new UserDetails();
			newUser.setUserId(userId);
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setDob(user.getDob());
			newUser.setAddress(user.getAddress());
			newUser.setContactNo(user.getContactNo());
			newUser.setEmail(user.getContactNo());
			newUser.setPassword(user.getPassword());
			newUser.setRole(user.getRole());
			userRepository.save(newUser);
			userRes.setUserId(userId);
			userRes.setUserStatus("Registered");
			userRes.setResponseStatus("Success");
			return userRes;
		}
	}

	public UserResponse buildUserResponse(UserDetails userInfo, String passwordUserInp) {
		UserResponse userDetailsResponse = new UserResponse();
		String passwordFromDB = userInfo.getPassword();
		if (userInfo != null) {
			userDetailsResponse.setUserId(userInfo.getUserId());
			Boolean chkpwd = checkPassword(passwordFromDB, passwordUserInp);
			if (chkpwd) {
				userDetailsResponse.setUserStatus("ExistingUser");
				userDetailsResponse.setResponseStatus("Success");
				userDetailsResponse.setRole(userInfo.getRole());
			} else {
				userDetailsResponse.setUserStatus("ExistingUser");
				userDetailsResponse.setResponseStatus("PasswordIncorrect");
			}
		}
		return userDetailsResponse;
	}

	public Boolean checkPassword(String passwordFromDB, String passwordInp) {
		if(passwordFromDB.equals(passwordInp)) {
		  return true;	
		}
		else {
			return false;
		}
	}

}
