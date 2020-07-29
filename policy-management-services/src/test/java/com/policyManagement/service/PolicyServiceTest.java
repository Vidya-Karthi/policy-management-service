package com.policyManagement.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.policymanagement.model.PolicyDetails;
import com.policymanagement.model.PolicyDetailsResponse;
import com.policymanagement.model.RegisterUser;
import com.policymanagement.model.UserDetails;
import com.policymanagement.model.UserPolicyDetails;
import com.policymanagement.model.UserPolicyResponse;
import com.policymanagement.repomanager.PolicyRepository;
import com.policymanagement.repomanager.PolicyUpdRepository;
import com.policymanagement.repomanager.UserPolicyRepository;
import com.policymanagement.service.impl.PolicyServiceImpl;
import com.policymanagement.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceTest {
	
	//private MockMvc mockMvc;
	
	@Mock
	private PolicyRepository policyRepository;
	
	@Mock
	private UserPolicyRepository userPolicyRepository;
	
	@Mock
	private PolicyUpdRepository policyupd;
	
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@InjectMocks
	private PolicyServiceImpl policyService;
	
	
	private UserDetails newUser = new UserDetails();
	
	private RegisterUser newUser1 = new RegisterUser();
	private List<PolicyDetailsResponse> expPolicyResp =  new ArrayList<>(); 
	
	private List<UserPolicyResponse> expUserResp = new ArrayList<>(); 

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		PolicyDetailsResponse policy = new PolicyDetailsResponse();
		policy.setPolicyId("1");
		policy.setPolicyDetails("Policy Details 1");
		policy.setPolicyName("Health Insurance Policy 1");
		expPolicyResp.add(policy);
		
		UserPolicyResponse expUserResp = new UserPolicyResponse(); 
		expUserResp.setUserId("vidya2604");
		expUserResp.setAmountPaid("1000.0");
		expUserResp.setPolicyEndDate("12-06-2020");
		expUserResp.setPolicyName("Health Insurance Policy 1");
		expUserResp.setPolicyNo("1");
		expUserResp.setValid("No");
		
	}
	
    @Test
    public void getPolicyInfo() {
    	List<PolicyDetailsResponse> policyResp = new ArrayList<>(); 
    	List<PolicyDetails> policyDetails = new ArrayList<>(); 
    	Mockito.doReturn(policyDetails).when(policyRepository).findAll();
    	policyResp = policyService.getPolicyDetails();
    	for(PolicyDetailsResponse policy: policyResp) {
    		if (policy.getPolicyId() == "1") {
    		assertEquals(expPolicyResp,policy);
    		}
    	}
    }
    
    @Test
    public void getUserPolicyInfo() {
    	List<UserPolicyResponse> userResp = new ArrayList<>(); 
    	List<UserPolicyDetails> userPolicyDetails = new ArrayList<>(); 
    	String userId = "vidya2604";
    	Mockito.doReturn(userPolicyDetails).when(userPolicyRepository).findByUserPolicyDetailsIdUserId(userId);
    	userResp = policyService.getUserPolicyDetails(userId);
    	for(UserPolicyResponse userPolicy: userResp) {
    		if (userPolicy.getPolicyNo() == "1") {
    		assertEquals(expUserResp,userPolicy);
    		}
    }
    
    }
}


