'use strict';

angular.module('myApp').controller('CreateRepoController', ['$scope', 'UserService',  function($scope, UserService) {
    var self = this;

    self.createdSchema = '';
    self.newRepo={repo:'',folder:'',index:'',schema:''};
    

    self.cancelCreateRepo = cancelCreateRepo;
    self.createRepo = createRepo;
    self.createSchema =createSchema;


    function cancelCreateRepo()
    {
    	console.log("CreateRepoController --> cancelCreateUser");

    	window.location = "repoManagement";
    }
    
    function createRepo() 
    {
    	console.log("CreateRepoController --> createRepo");
    	
    	if(IsJsonString(self.createdSchema))
	  	{
    		var repoAttributes = JSON.parse(self.createdSchema);
    		//Object.keys(repoAttributes);
    		self.newRepo.schema = Object.keys(repoAttributes).join();
	  	}

    	if(self.newRepo.schema == '')
		{
    		alert("Schema is not valid JSON");
		}
    	else
		{
    		 UserService.createRepo(self.newRepo)
             .then(
        		function(response) 
    	        {
        			if(response == 0)
            		{
                		alert("Unable to create repo");
            		}
        			else
    				{
                		alert("Repo created succesfully");
    				}
    			 
    	        },
             function(errResponse){
                 console.error('Error while creating User');
             }
         );
		}
    }
    
    function IsJsonString(str) {
        try {
            JSON.parse(str);
        } catch (e) {
            return false;
        }
        return true;
    }

    
    function readSingleFile(evt) 
    {
        //Retrieve the first (and only!) File from the FileList object
        var f = evt.target.files[0]; 

        if (f) {
          var r = new FileReader();
          r.onload = function(e) { 
    	      var contents = e.target.result;
    	      
    	      self.createdSchema = contents;

          }
          r.readAsText(f);
        } else { 
          alert("Failed to load file");
        }
      }
    
    function createSchema()
    {
        document.getElementById('schemaBtn').style.visibility = 'hidden';

    	document.getElementById('inputSchemaDiv').style.display = "block";
    }
    

    document.getElementById('schemaFile').addEventListener('change', readSingleFile, false);

}]);
