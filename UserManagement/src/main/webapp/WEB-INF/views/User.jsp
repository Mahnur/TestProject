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
		
		li {
		    float: left;
		}
		
		li a {
		    display: block;
		    color: #666;
		    text-align: center;
		    padding: 14px 16px;
		    text-decoration: none;
		}
		
		li a:hover:not(.active) {
		    background-color: #ddd;
		}
		
		li a.active {
		    color: white;
		    background-color: #4CAF50;
		}
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="" class="ng-cloak">

	<ul>
  		<li><a class="active" href="searching">Search Files</a></li>
 
	</ul>    
	
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
  </body>
</html>