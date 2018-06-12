'use strict';

angular.module('myApp').controller('LoginController', ['$scope', 'UserService',  function($scope, UserService) {
    var self = this;
    //self.email = "";
    //self.password = "";

    self.user={id:12,username:'',role:'',email:'',password:''};

    
    self.login = login;


    function login() 
    {
    	console.log("logincontroller --> login");
       // window.location = 'test';
        //$state.transitionTo('test');
        //var App = angular.module('myApp2',[]);
    	
    	//window.location = 'test';

    	console.log("logincontroller --> login | "+self.user.username+" | "+self.user.password);
    	



    	UserService.loginUser(self.user)
        .then(
        function(nextPage) 
        {
        	console.log("logincontroller --> login | nextPage :"+nextPage);

        	if(nextPage == 3)
    		{
        		alert("User does not exists");
    		}
        	else if(nextPage == 0)
    		{
        		alert("Password is incorrect");
    		}
        	else if(nextPage == 1)
    		{
        		window.location = "userManagement";
    		}
        	else
    		{
        		window.location = 'userPage';
    		}
        },
        function(errResponse){
            console.error('Error while login User : '+ errResponse);
        }
    );
    }
}]);
