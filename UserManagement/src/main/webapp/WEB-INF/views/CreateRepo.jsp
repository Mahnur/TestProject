<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <style>
		span{
		color:red;
		}


		input[type=text], input[type=file]{
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

	<div class="w3-container"  ng-controller="CreateRepoController as repoCtrl">

	<form name="myForm" ng-hide="hideform">
	  <h3>Create New Repo:</h3>
	  
	  <p>
	    <label>Enter Repo Name:</label>
	    <input class="w3-input w3-border" type="text"  name="repoName" ng-model="repoCtrl.newRepo.repo"  placeholder="Repo Name" required>
		<span ng-show="myForm.repoName.$touched && myForm.repoName.$invalid">*Repo Name is required.</span>
	  <br>
	  </p>
	  
	  
	  <p>
	    <label>Schema:</label>
	    <input class="w3-input w3-border " type="file"  name="schemaFile" id="schemaFile" >
	  <br>
	  </p>
	  
	  
	  <div id="inputSchemaDiv" style="display:none;">
	  	<input class="w3-input w3-border w3-centre" type="text"  name="createSchema" ng-model="repoCtrl.createdSchema" style="width:75%; height:150px;">
	  </div>
	  
	  
	  <button class="w3-btn w3-green w3-ripple w3-margin-left w3-right"  ng-disabled="myForm.$invalid" ng-click="repoCtrl.createRepo()">&#9998; Create</button>
	  <button class="w3-btn w3-red w3-ripple w3-margin-left w3-right" ng-click="repoCtrl.cancelCreateRepo()">&#9998; Cancel</button>
	  
	  <br>
	  <br>
  	  <button id="schemaBtn" class="w3-btn w3-light-grey w3-ripple w3-centre" ng-click="repoCtrl.createSchema()">&#9998; Create your own schema</button>
	  
	  
	</form>
	
	
	
	</div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/create_repo_controller.js' />"></script>
      
  </body>
</html>