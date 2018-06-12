'use strict';

angular.module('myApp').controller('RepoManagementController', ['$scope', 'UserService',  function($scope, UserService) {
    var self = this;

   
    //self.allRepos=[];

    
    self.deleteRepo = deleteRepo;
    self.createRepo = createRepo;

    fetchAllRepos();

    function fetchAllRepos()
    {
    	console.log("RepoManagementController --> fetchAllRepos");

        UserService.fetchAllRepos()
            .then(
            function(d) {
                self.allRepos = d;
            },
            function(errResponse){
                console.error('Error while fetching repos');
            }
        );
    }
    
    function createRepo()
    {
    	console.log("RepoManagementController --> createRepo");

		window.location = "createRepo";
    }
        
    function deleteRepo(repoName)
    {
    	console.log("RepoManagementController --> deleteRepo");
    	
    	var msg = "Are you sure to delete repo : "+ repoName;
    	
    	if (confirm(msg)) 
    	{
    		UserService.deleteRepo(repoName)
            .then(
            		fetchAllRepos,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
        }

        
    }
    
        
    
}]);
