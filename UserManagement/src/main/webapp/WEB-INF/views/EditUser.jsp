<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <style>
		span{
		color:red;
		}


		input[type=text], input[type=password], input[type=email], select{
		    width: 50%;
		    padding: 12px 20px;
		    margin: 8px 0;
		    display: inline-block;
		    border: 1px solid #ccc;
		    box-sizing: border-box;
		}
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/w3.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">

	<div class="w3-container"  ng-controller="EditUserController as userCtrl">

	<form name="myForm" ng-hide="hideform">
	  <h3>Edit User:</h3>
	  
	  <p>
	    <label>Enter ID:</label>
	    <input class="w3-input w3-border" type="text"  ng-model="userCtrl.user.id" ng-disabled="true" placeholder="">
	  <br>
	  </p>
	  
	  
	  <p>
	    <label>Enter User Name:</label>
	    <input class="w3-input w3-border" type="text"  name="uName" ng-model="userCtrl.user.username"  placeholder="User Name" required>
		<span ng-show="myForm.uName.$touched && myForm.uName.$invalid">*User name is required.</span>
	  <br>
	  </p>
	  
	   <p>
	    <label>Enter User Role:</label>
	    <select class="w3-select w3-border" ng-model="userCtrl.selectedRole" ng-options="role as role.value for role in userCtrl.userRoles">
		</select>
	  <br>
	  </p>
	  
	  <p>
	    <label>Enter Email:</label>
        <input type="email" ng-model="userCtrl.user.email" name="email" class="email w3-input w3-border" placeholder="Enter your Email" required/>
        <span ng-show="myForm.email.$touched && myForm.email.$invalid">This field is invalid </span>
	  </p>
	  
	  <p>
	    <label>Enter Password:</label>
	    <input class="w3-input w3-border" type="password"  name="passw1" ng-model="userCtrl.user.password" placeholder="Password" required>
		<span ng-show="myForm.passw1.$touched && myForm.passw1.$invalid">*Enter Password.</span>
	  <br>
	  </p>
	  
	  <p>
	    <label>Re-enter Password:</label>
	    <input class="w3-input w3-border" type="password"  name="passw2" ng-model="userCtrl.confirmPassword" placeholder="Re-enter Password" required>
		<span ng-show="myForm.passw2.$touched && myForm.passw2.$invalid">*Re-enter your password.</span>
		
	  <br>
	  </p>

	  
	  <button class="w3-btn w3-green w3-ripple" ng-click="userCtrl.updateUser()">&#9998; Update</button>
	  <button class="w3-btn w3-red w3-ripple" ng-click="userCtrl.cancelUpdateUser()">&#9998; Cancel</button>
	  
	</form>
	
	
	
	</div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/edit_user_controller.js' />"></script>
      <script src="<c:url value='/static/js/controller/usermanagement_controller.js'/>"></script>
      
      
  </body>
</html>