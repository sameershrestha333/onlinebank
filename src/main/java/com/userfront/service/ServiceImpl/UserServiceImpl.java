package com.userfront.service.ServiceImpl;



import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userfront.dao.RoleDao;
import com.userfront.dao.UserDao;
import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;
import com.userfront.service.AccountService;
import com.userfront.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOG=LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private  UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountService accountService;

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public boolean checkUserExists(String username, String email) {
		if(checkUsernameExists(username)||checkEmailExists(email)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean checkUsernameExists(String username) {
		if(null!=findByUsername(username)){
			return true;
		}
		return false;
		
	}

	@Override
	public boolean checkEmailExists(String email) {
		
		if(null!=findByEmail(email)){
			return true;
		}
		return false;
	}

	@Override
	public void save(User user) {

		userDao.save(user);
	}
	
	@Override
	public User createUser(User user,Set<UserRole> userRoles){
		User localUser=userDao.findByUsername(user.getUsername());
		
		if(localUser!=null){
			LOG.info("User with username {} already exist. Nothing will be done.",user.getUsername());
		}else{
			//1. set encrypted password
			String encryptedPassword=passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			for(UserRole ur:userRoles){
				roleDao.save(ur.getRole());
			}
			//2 add user role
			user.getUserRoles().addAll(userRoles);
			
			
			//binding primaryAccount and savingsAccount.
			user.setPrimaryAccount(accountService.createPrimaryAccount());
			user.setSavingsAccount(accountService.createSavingsAccount());
			
			localUser=userDao.save(user);
			
			
		}
		
		
		return localUser;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}
	
	
}
