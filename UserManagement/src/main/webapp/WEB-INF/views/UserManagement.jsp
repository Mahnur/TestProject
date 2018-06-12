<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <style>
      ul {
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		    overflow: hidden;
		    border: 1px solid #e7e7e7;
		    background-color: #f3f3f3;
		}
		
		a{
		    float: left;
		}
		
		 a {
		    display: block;
		    color: #666;
		    text-align: center;
		    padding: 14px 16px;
		    text-decoration: none;
		}
		
		 a:hover:not(.active) {
		    background-color: #ddd;
		}
		 a.active {
		    color: white;
		    background-color: #4CAF50;
		}
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
     <link href="<c:url value='/static/css/w3.css' />" rel="stylesheet"></link>
     
  </head>
  <body ng-app="myApp" class="ng-cloak">

	<ul>
	  <a class="active" href="">User Management</a>
	  <a href="repoManagement">Repository Management</a>
	  <a href="#ingestion ">Ingestion of Files</a>
	  <a href="#searching">Search Files</a>
	</ul>
	
	<div class="w3-container"  ng-controller="UserManagementController as usermanagementCtrl">
	
		<br>
		<br>

		<p>
			<button class="w3-btn w3-green w3-ripple w3-right" ng-click="usermanagementCtrl.createUser()">+ Add</button>
		<br>
		</p>
		
		
		<br>
		<br>
		
		<table class="w3-table w3-bordered w3-striped">
		  <tr>
		    <th>ID</th>
		    <th>Name</th>
		    <th>Role</th>
		    <th>Email</th>
		    <th>Password</th>
		    <th>Actions</th>
		  </tr>
		  <tr ng-repeat="currentUser in usermanagementCtrl.users track by $index">
		    
		    <td>{{ currentUser.id }}</td>
		    <td>{{ currentUser.username }}</td>
		    <td>{{ currentUser.role }}</td>
		    <td>{{ currentUser.email }}</td>
		    <td>{{ currentUser.password }}</td>
		    <td>
		      <button class="w3-btn w3-green w3-ripple" ng-click="usermanagementCtrl.editUser(currentUser)">&#9998; Edit</button>
		      <button class="w3-btn w3-red w3-ripple" ng-click="usermanagementCtrl.deleteUser(currentUser.id)">&#9998; Delete</button>
		    </td>
		    
		  </tr>
		</table>
		<br>
	
	</div>
	      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/usermanagement_controller.js'/>"></script>
      
  </body>
</html>