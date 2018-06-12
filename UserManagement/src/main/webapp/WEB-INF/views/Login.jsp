<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="LoginController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Login Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="loginForm" class="form-horizontal">

                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Username</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.username" name="username" class="form-control input-sm" placeholder="Enter your Username" required/>
                                  <div class="has-error" ng-show="loginForm.$dirty">
                                      <span ng-show="loginForm.username.$invalid">This field is required </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Password</label>
                              <div class="col-md-7">
                                  <input type="password" ng-model="ctrl.user.password" name="password" class="form-control input-sm" placeholder="Enter your Password" required/>
                                  <div class="has-error" ng-show="loginForm.$dirty">
                                      <span ng-show="loginForm.password.$invalid">This field is required </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                     

                      <div class="row">
                          <div class="form-actions floatRight">
                              <button type="button" ng-click="ctrl.login()" class="btn btn-warning btn-sm" ng-disabled="loginForm.$invalid">Login</button>
                              
                          </div>
                      </div>
                  </form>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/login_controller.js' />"></script>
  </body>
</html>