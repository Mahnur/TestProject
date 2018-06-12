package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Repo;
import com.websystique.springmvc.model.User;



public interface UserService {
	
	User findById(long id);
	
	User findByName(String name);
	
	boolean saveUser(User user);
	
	boolean updateUser(User user);
	
	void deleteUserById(String id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
	public String getUserLoginPage(User user);
	
	
	
	boolean saveRepo(Repo repo);
	
	List<Repo> findAllRepos();
	
	void deleteRepoByName(String repoName);


	
}
