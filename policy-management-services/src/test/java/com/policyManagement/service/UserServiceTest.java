package com.policyManagement.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.policymanagement.model.LoginRequest;
import com.policymanagement.model.RegisterUser;
import com.policymanagement.model.UserDetails;
import com.policymanagement.model.UserResponse;
import com.policymanagement.repomanager.UserRepository;
import com.policymanagement.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	//private MockMvc mockMvc;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;
	
//	@Autowired
//	private PolicyService policyService;
//	
	
	private LoginRequest loginRequest = new LoginRequest();
	
	private RegisterUser userExist = new RegisterUser();
	
	private UserDetails newUser = new UserDetails();
	
	private RegisterUser newUser1 = new RegisterUser();

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		loginRequest.setUserId("vidya2604");
		loginRequest.setPassword("abcdefgh");
		
		userExist.setUserId("vidya2604");
		userExist.setFirstName("vidya");
		userExist.setLastName("karthi");
		userExist.setRole("user");
		userExist.setPassword("tryit");
		userExist.setEmail("user@gmail.com");
		userExist.setDob("26/04/2000");
		userExist.setAddress("1st street");
		userExist.setContactNo("1231231121");
		
		newUser.setUserId("guha2604");
		newUser.setFirstName("Guhan");
		newUser.setLastName("karthi");
		newUser.setRole("user");
		newUser.setPassword("tryit");
		newUser.setEmail("guhauser@gmail.com");
		newUser.setDob("26/04/2010");
		newUser.setAddress("1st street");
		newUser.setContactNo("1241231121");
		
		newUser1.setUserId("guha2604");
		newUser1.setFirstName("Guhan");
		newUser1.setLastName("karthi");
		newUser1.setRole("user");
		newUser1.setPassword("tryit");
		newUser1.setEmail("guhauser@gmail.com");
		newUser1.setDob("26/04/2010");
		newUser1.setAddress("1st street");
		newUser1.setContactNo("1241231121");
	}
	
    @Test
    public void checkUserExist() {
    	UserResponse userResp = new UserResponse();
    	UserDetails userInfo =  new UserDetails(); 
    	userInfo.setUserId("vidya2604");
    	userInfo.setFirstName("vidya");
    	userInfo.setLastName("karthick");
    	userInfo.setDob("26/04/1982");
    	userInfo.setAddress("2nd street");
    	userInfo.setContactNo("1231211234");
    	userInfo.setPassword("abcdefgh");
    	userInfo.setEmail("vidya@gmail.com");
    	userInfo.setRole("user");
    	//Mockito.when(userRepository.findByUserId("vidya2604")).thenReturn(userInfo);
    	Mockito.doReturn(userInfo).when(userRepository).findByUserId(Mockito.anyString());
    	userResp = userService.getUserById(loginRequest);
    	assertEquals("vidya2604", userResp.getUserId());
    	assertEquals("ExistingUser", userResp.getUserStatus());
    	assertEquals("Success", userResp.getResponseStatus());
    	assertEquals("user", userResp.getRole());
    }
    
    @Test
    public void checkUserNotExist() {
    	UserResponse userResp = new UserResponse();
    	UserDetails userInfo =  new UserDetails(); 
    	userInfo = null;
    	//Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(new UserDetails());
    	Mockito.doReturn(userInfo).when(userRepository).findByUserId(Mockito.anyString());
    	userResp = userService.getUserById(loginRequest);
//    	assertEquals("vidya0426", userResp.getUserId());
    	assertEquals("NewUser", userResp.getUserStatus());
    	assertEquals("Failure", userResp.getResponseStatus());
    	assertEquals(null, userResp.getRole());
    }
    
    @Test
    public void checkExistingUser() {
    	UserResponse userResp = new UserResponse();
    	UserDetails userInfo =  new UserDetails(); 
    	userInfo.setUserId("vidya2604");
    	userInfo.setFirstName("vidya");
    	userInfo.setLastName("karthick");
    	userInfo.setDob("26/04/1982");
    	userInfo.setAddress("2nd street");
    	userInfo.setContactNo("1231211234");
    	userInfo.setPassword("abcdefgh");
    	userInfo.setEmail("vidya@gmail.com");
    	userInfo.setRole("user");
    	//Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(new UserDetails());
    	Mockito.doReturn(userInfo).when(userRepository).findByUserId(Mockito.anyString());
    	userResp = userService.registerUser(userExist);
    	assertEquals("ExistingUser", userResp.getUserStatus());
    	assertEquals("Success", userResp.getResponseStatus());
    	assertEquals("user", userResp.getRole());
    }
    
    
    @Test
    public void checkNewUser() {
    	UserResponse userResp = new UserResponse();
    	UserDetails userInfo =  new UserDetails(); 
    	userInfo = null;
    	Mockito.doReturn(userInfo).when(userRepository).findByUserId(Mockito.anyString());
    	//Mockito.when(userRepository.save(newUser)).thenReturn(null);
    	userResp = userService.registerUser(newUser1);
    	assertEquals("Registered", userResp.getUserStatus());
    	assertEquals("Success", userResp.getResponseStatus());
    }
 
}


