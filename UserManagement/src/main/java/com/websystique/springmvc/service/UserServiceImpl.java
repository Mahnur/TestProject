package com.websystique.springmvc.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Repo;
import com.websystique.springmvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	
	

	public List<User> findAllUsers() 
	{
		UserManagementDB userDB = new UserManagementDB();
		userDB.connectToTable();
		
		return userDB.getAllUsers();
	}
	
	public User findById(long id) {
//		for(User user : users){
////			if(user.getId() == id){
////				return user;
////			}
//		}
		return null;
	}
	
	public User findByName(String name) {
//		for(User user : users){
//			if(user.getUsername().equalsIgnoreCase(name)){
//				return user;
//			}
//		}
		return null;
	}
	
	public boolean saveUser(User user) 
	{
		//user.setId(counter.incrementAndGet());
		//users.add(user);
		
		UserManagementDB userDB = new UserManagementDB();
		
		userDB.connectToTable();
		return userDB.createUser(user);
		
	}

	public boolean updateUser(User user) 
	{
		UserManagementDB userDB = new UserManagementDB();
		
		userDB.connectToTable();
		return userDB.updateUser(user);
	}

	public void deleteUserById(String id) {
		
		UserManagementDB userDB = new UserManagementDB();
		
		userDB.connectToTable();
		userDB.deleteUser(id);
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		//users.clear();
	}

	
	public String getUserLoginPage(User user)
	{
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		String loginPage = "";
		
		if(user.getUsername().equalsIgnoreCase("admin") && user.getPassword().equalsIgnoreCase("admin"))
			loginPage = "admin";
		else
		{
		
			UserManagementDB userDB = new UserManagementDB();
			
			userDB.connectToTable();
			loginPage = userDB.getUserLoginPage(user);
		}
		
		
		return loginPage;
	}
	
	public boolean saveRepo(Repo repo) 
	{
		//user.setId(counter.incrementAndGet());
		//users.add(user);
		
		RepoDB repoDB = new RepoDB();
		
		repoDB.connectToTable();
		
		return repoDB.createRepo(repo);
	}
	
	public List<Repo> findAllRepos() 
	{
		RepoDB repoDB = new RepoDB();
		repoDB.connectToTable();
		
		return repoDB.getAllRepos();
	}
	
	public void deleteRepoByName(String repoName) 
	{
		RepoDB repoDB = new RepoDB();
		
		repoDB.connectToTable();
		repoDB.deleteUser(repoName);
	}


}
