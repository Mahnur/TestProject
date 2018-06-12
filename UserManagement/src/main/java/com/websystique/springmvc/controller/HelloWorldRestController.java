package com.websystique.springmvc.controller;
 
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Node;

import com.mysql.fabric.xmlrpc.Client;
import com.websystique.springmvc.model.Repo;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;
 
@RestController
@EnableWebMvc
public class HelloWorldRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/fetchusers/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() 
    {
        List<User> users = userService.findAllUsers();
        
        if(users != null)
            System.out.println("Fetching User with usersid " + users.size());

        
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
 
    
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Integer> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getEmail());
 
//        if (userService.isUserExist(user)) {
//            System.out.println("A User with name " + user.getUsername() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
 
        boolean isUserCreated = userService.saveUser(user);
        
        if(isUserCreated)
            return new ResponseEntity<Integer>(1,HttpStatus.OK);
        else
            return new ResponseEntity<Integer>(0,HttpStatus.OK);

 

    }
 
    
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        System.out.println("Updating User ");
         
         
        boolean isUserUpdated = userService.updateUser(user);

        if(isUserUpdated)
            return new ResponseEntity<Integer>(1,HttpStatus.OK);
        else
            return new ResponseEntity<Integer>(0,HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
//        User user = userService.findById(id);
//        if (user == null) {
//            System.out.println("Unable to delete. User with id " + id + " not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
    //-------------------Login a User--------------------------------------------------------
    
    
        
    
//    @RequestMapping(value = "/login/", method = RequestMethod.GET)
//    public ResponseEntity<Boolean> loginUser(@RequestBody User user) 
//    {
//        System.out.println("login User " + user.getUsername());
// 
//        if (user.getEmail().equalsIgnoreCase("admin@admin")) 
//        {
//            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<Boolean>(false,HttpStatus.OK);
//
// 
//    }
    
    @RequestMapping(value = "/login/", method = RequestMethod.PUT)
    public ResponseEntity<Integer> loginUser(@RequestBody User user)  
    {

    	System.out.println("login User " + user.getUsername());
    	
    	String loginResult = userService.getUserLoginPage(user);
    	
    	if(loginResult.equalsIgnoreCase("admin"))
            return new ResponseEntity<Integer>(1,HttpStatus.OK);
    	else if(loginResult.equalsIgnoreCase("userPage"))
            return new ResponseEntity<Integer>(2,HttpStatus.OK);
    	else if(loginResult.equalsIgnoreCase("user_error"))
            return new ResponseEntity<Integer>(3,HttpStatus.OK);
    	else 
            return new ResponseEntity<Integer>(0,HttpStatus.OK);

    	 
//        if (user.getEmail().equalsIgnoreCase("admin@admin")) 
//        {
//            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<Boolean>(false,HttpStatus.OK);
    }
    
  //-------------------Create a Repo--------------------------------------------------------
    
    @RequestMapping(value = "/repo/", method = RequestMethod.POST)
    public ResponseEntity<Integer> createRepo(@RequestBody Repo repo,    UriComponentsBuilder ucBuilder) {
 
//        if (userService.isUserExist(user)) {
//            System.out.println("A User with name " + user.getUsername() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
    	
        boolean isRepoCreated = userService.saveRepo(repo);
        

        if(isRepoCreated)
            return new ResponseEntity<Integer>(1,HttpStatus.OK);
        else
            return new ResponseEntity<Integer>(0,HttpStatus.OK);
       /* RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));
SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
searchSourceBuilder.query(QueryBuilders.matchAllQuery());            
searchSourceBuilder.aggregation(AggregationBuilders.terms("top_10_states").field("state").size(10));
SearchRequest searchRequest = new SearchRequest();
searchRequest.indices("social-*");
searchRequest.source(searchSourceBuilder);
SearchResponse searchResponse = client.search(searchRequest);*/

 

    }
    
  //-------------------Retrieve All Users--------------------------------------------------------
    
    private Object nodeBuilder() {
		// TODO Auto-generated method stub
		return null;
	}



	@RequestMapping(value = "/fetchrepos/", method = RequestMethod.GET)
    public ResponseEntity<List<Repo>> listAllRepos() 
    {
        List<Repo> repos = userService.findAllRepos();
        
        if(repos != null)
            System.out.println("Fetching User with usersid " + repos.size());

        
        if(repos.isEmpty()){
            return new ResponseEntity<List<Repo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Repo>>(repos, HttpStatus.OK);
    }
    
  //------------------- Delete a User --------------------------------------------------------
    
    @RequestMapping(value = "/deleterepo/{repoName}", method = RequestMethod.DELETE)
    public ResponseEntity<Repo> deleteRepo(@PathVariable("repoName") String repoName) {
        System.out.println("Fetching & Deleting User with id " + repoName);
 
//        User user = userService.findById(id);
//        if (user == null) {
//            System.out.println("Unable to delete. User with id " + id + " not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
 
        userService.deleteRepoByName(repoName);
        return new ResponseEntity<Repo>(HttpStatus.NO_CONTENT);
    }
 

    

 
}