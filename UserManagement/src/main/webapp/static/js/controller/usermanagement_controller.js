'use strict';

angular.module('myApp').controller('UserManagementController', ['$scope', 'UserService',  function($scope, UserService) {
    var self = this;

    self.confirmPassword = '';
    self.newUser={id:'',username:'',role:'',email:'',password:''};
    
    var editCurrentUser;
    var globalVariable;
    
    self.allUsers=[];

    
    self.editUser = editUser;
    self.deleteUser = deleteUser;
    self.updateUser = updateUser;
    self.createUser = createUser;

    fetchAllUsers();

    function fetchAllUsers()
    {
    	console.log("UserManagementController --> fetchAllUsers");

        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    
    function createUser()
    {
		window.location = "createUser";
    }

    
    function editUser(user)
    {

    	self.newUser = user;
    	
    	console.log("UserManagementController --> editUser");
    	
    	window.localStorage.setItem('editUser', JSON.stringify(user));


    	window.location = "editUser";
    	
    }
    
    function deleteUser(userID)
    {
    	console.log("UserManagementController --> deleteUser");
    	
    	var msg = "Are you sure to delete user : "+ userID;
    	
    	if (confirm(msg)) 
    	{
    		UserService.deleteUser(userID)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
            );
        } 
       
    }
    
    function updateUser()
    {
    	if(self.confirmPassword != self.newUser.password)
    	{
    		alert("Passwords don't match");

    	}
    	else
    	{
    		console.log("UserManagementController --> updateUser");

            UserService.updateUser(self.newUser, self.newUser.id)
                .then(
            		function(response) 
        	        {
            		    fetchAllUsers();
            			if(response == 0)
                		{
                    		alert("Unable to update user");
                		}
            			else
        				{
                    		alert("User updated succesfully");
        				}
        			 
        	        }
                ,
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    	}
    	
    	
    }
    
    
}]);
