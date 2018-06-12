'use strict';

angular.module('myApp').controller('CreateUserController', ['$scope', 'UserService',  function($scope, UserService) {
    var self = this;

    self.confirmPassword = '';
    self.selectedRole = '';
    self.user={id:'',username:'',role:'',email:'',password:''};
    
    self.userRoles = [{value: 'Admin'},{value: 'User'}];

//    var roleSelect = document.getElementById('uRole');
//	console.log("CreateUserController --> roleSelect: "+roleSelect);
//
//    roleSelect.selectedIndex = 0;


    self.cancelCreateUser = cancelCreateUser;
    self.createUser = createUser;
    
    window.onload = init;

    function init(){
      // the code to be called when the dom has loaded
      // #document has its nodes
    	
    	var roleSelect = document.getElementById('uRole');
    	console.log("init --> roleSelect: "+roleSelect);

        roleSelect.selectedIndex = 0;

    }


    function cancelCreateUser()
    {
    	console.log("logincontroller --> cancelCreateUser");

    	window.location = "userManagement";
    }
    
    function createUser() 
    {
    	console.log("logincontroller --> login");
       // window.location = 'test';
        //$state.transitionTo('test');
        //var App = angular.module('myApp2',[]);
    	
    	//window.location = 'test';

    	console.log("logincontroller --> login | "+self.user.email+" | "+self.user.password);

    	if(self.confirmPassword != self.user.password)
    	{
    		alert("Passwords don't match");

    	}
    	else
    	{
    		self.user.role = self.selectedRole.value;
    		
        	console.log("logincontroller --> login(role) | "+self.user.email+" | "+self.user.password+" | "+self.user.role);

    		 UserService.createUser(self.user)
             .then(
        		function(response) 
    	        {
        			if(response == 0)
            		{
                		alert("Unable to create user");
            		}
        			else
    				{
                		alert("User created succesfully");
    				}
    			 
    	        },
             function(errResponse){
                 console.error('Error while creating User');
             }
         );
    	}

    }
}]);
