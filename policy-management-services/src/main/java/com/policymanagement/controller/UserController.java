package com.policymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.policymanagement.model.LoginRequest;
import com.policymanagement.model.PolicyDetailsResponse;
import com.policymanagement.model.RegisterUser;
import com.policymanagement.model.UserPolicyRequest;
import com.policymanagement.model.UserPolicyResponse;
import com.policymanagement.model.UserResponse;
import com.policymanagement.service.PolicyService;
import com.policymanagement.service.UserService;


@Controller
@RequestMapping("/policyApp")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PolicyService policyService;
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> validateUser(@RequestBody final LoginRequest loginRequest) throws JsonProcessingException {
        
		UserResponse userInfo = userService.getUserById(loginRequest);
		return new ResponseEntity<UserResponse>(userInfo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> register(@RequestBody final RegisterUser userRequest) throws JsonProcessingException {
        
		UserResponse registerUser = userService.registerUser(userRequest);
		return new ResponseEntity<UserResponse>(registerUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/policyDetails", method = RequestMethod.GET)
	public ResponseEntity<List<PolicyDetailsResponse>> getPolicyDetails() throws JsonProcessingException {
        
		List<PolicyDetailsResponse> policyDetails = policyService.getPolicyDetails();
		return new ResponseEntity<List<PolicyDetailsResponse>>(policyDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userPolicyDetails", method = RequestMethod.POST)
	public ResponseEntity<List<UserPolicyResponse>> getUserPolicyInfo(@RequestBody final UserPolicyRequest userReq) throws JsonProcessingException {
        
		List<UserPolicyResponse> userPolicyDetails = policyService.getUserPolicyDetails(userReq.getUserId());
		return new ResponseEntity<List<UserPolicyResponse>>(userPolicyDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/savePolicyDetails", method = RequestMethod.POST)
	public ResponseEntity<List<PolicyDetailsResponse>> savePolicyInfo(@RequestBody final List<PolicyDetailsResponse> policyUpd) throws JsonProcessingException {
		List<PolicyDetailsResponse> savePolicyDetails = policyService.savePolicyDetails(policyUpd);
		return new ResponseEntity<List<PolicyDetailsResponse>>(savePolicyDetails, HttpStatus.OK);
	}

}
