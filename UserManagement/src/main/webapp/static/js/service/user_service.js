'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/Spring4MVCAngularJSExample/';

    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser,
        loginUser:loginUser,
        fetchAllRepos:fetchAllRepos,
        createRepo:createRepo,
        deleteRepo:deleteRepo

    };

    return factory;

    function fetchAllUsers() {
    	
    	var fetchUsersUrl = REST_SERVICE_URI+'fetchusers/';

        var deferred = $q.defer();
        $http.get(fetchUsersUrl)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createUser(user) {
    	
    	console.log("userservice --> createUser");

    	var loginUrl = REST_SERVICE_URI+'user/';

        var deferred = $q.defer();
        $http.post(loginUrl, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateUser(user, id) {
    	var updateUserUrl = REST_SERVICE_URI+'updateuser/';

        var deferred = $q.defer();
        $http.put(updateUserUrl+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteUser(id) {
        var deferred = $q.defer();
        //deleteuser
        var deleteUserUrl = REST_SERVICE_URI+'deleteuser/';
        $http.delete(deleteUserUrl+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function loginUser(user) {
    	console.log("userservice --> loginUser | "+user+" | "+user.email+" | "+user.password);

    	var loginUrl = REST_SERVICE_URI+'login/';
//        var deferred = $q.defer();
//        $http.get(loginUrl, user)
//            .then(
//            function (response) {
//                deferred.resolve(response.data);
//            },
//            function(errResponse)
//            {
//                console.error('Error while login User');
//                deferred.reject(errResponse);
//            }
//        );
//        return deferred.promise;
        
        var deferred = $q.defer();
        $http.put(loginUrl, user)
            .then(
            function (response) {
            	console.log("userservice --> loginUser | response:"+response);
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while login Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllRepos() {
    	
    	var fetchReposUrl = REST_SERVICE_URI+'fetchrepos/';

        var deferred = $q.defer();
        $http.get(fetchReposUrl)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createRepo(repo) {
    	
    	console.log("userservice --> createRepo");

    	var createRepoUrl = REST_SERVICE_URI+'repo/';

        var deferred = $q.defer();
        $http.post(createRepoUrl, repo)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteRepo(repoName) {
        var deferred = $q.defer();
        //deleteuser
        var deleteUserUrl = REST_SERVICE_URI+'deleterepo/';
        $http.delete(deleteUserUrl+repoName)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


}]);
