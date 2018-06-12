package com.websystique.springmvc.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.websystique.springmvc.model.Repo;
import com.websystique.springmvc.model.User;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class RepoDB {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "test";
	
	/** The name of the table we are testing with */
	private final String tableName = "REPO_INFO";
	
	private final String repoDir = "repo_master";
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 */
	public boolean connectToTable()
	{
		// Connect to MySQL
		Connection conn = null;
		try 
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			conn = this.getConnection();
			System.out.println("Connected to database");
			
			DatabaseMetaData dbm= null;
			dbm = conn.getMetaData();

			ResultSet tables= null;
			tables = dbm.getTables(null, null, this.tableName, null);
			
			if (tables.next()) 
			{
				  // Table exists
				System.out.println("Table already exist");

			}
			else 
			{
			  // Table does not exist
				System.out.println("Table does not exist");

				String createString =
				        "CREATE TABLE " + this.tableName + " (" +
				        "Repo_Name varchar(30) NOT NULL, " +
				        "Repo_Folder varchar(40) NOT NULL, " +
				        "Repo_Index varchar(40) NOT NULL, " +
				        "Repo_Schema varchar(500) NOT NULL UNIQUE, " +
				        "PRIMARY KEY (Repo_Name))";
				
				System.out.println(createString);

				this.executeUpdate(conn, createString);
				System.out.println("Created a table");
			}

		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return false;
		}
		
		
		return false;
		
	}
	
	public boolean createRepo(Repo newRepo)
	{
		newRepo.setFolder(newRepo.getRepo()+"_folder");
		newRepo.setIndex(newRepo.getRepo()+"_index");

		boolean isQuerySuccess = true;
		
		String addNewUserCommand = "";
		
		addNewUserCommand = "INSERT INTO "+this.tableName+" (Repo_Name, Repo_Folder,Repo_Index,Repo_Schema) ";
		addNewUserCommand = addNewUserCommand + "VALUES ('"+newRepo.getRepo()+"','";
		addNewUserCommand = addNewUserCommand + newRepo.getFolder()+"','";
		addNewUserCommand = addNewUserCommand + newRepo.getIndex()+"','";
		addNewUserCommand = addNewUserCommand + newRepo.getSchema()+"')";
		
		System.out.println("addNewUserCommand: "+addNewUserCommand);
		Connection conn = null;
		try 
		{
			conn = this.getConnection();
			
			this.executeUpdate(conn, addNewUserCommand);
		}
		catch (SQLException e) 
		{
			isQuerySuccess = false;
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		
		
		File repoDirFile = new File("C:\\"+repoDir);
		
		if (!repoDirFile.exists()) {
            if (repoDirFile.mkdir()) 
            {
                System.out.println("repo master is created!");
            } 
            else {
                System.out.println("Failed to create repo master!");
            }
        }
		
		File repoFile = new File("C:\\"+repoDir+"\\"+newRepo.getRepo());
		
		if (!repoFile.exists()) {
            if (repoFile.mkdir()) 
            {
                System.out.println("repo is created!");
            } 
            else {
                System.out.println("Failed to create repo!");
            }
        }
		
		File repoFolderFile = new File("C:\\"+repoDir+"\\"+newRepo.getRepo()+"\\"+newRepo.getFolder());
		
		if (!repoFolderFile.exists()) {
            if (repoFolderFile.mkdir()) 
            {
                System.out.println("folder is created!");
            } 
            else {
                System.out.println("Failed to create folder!");
            }
        }
		
		File repoIndexFile = new File("C:\\"+repoDir+"\\"+newRepo.getRepo()+"\\"+newRepo.getIndex());
		
		if (!repoIndexFile.exists()) {
            if (repoIndexFile.mkdir()) 
            {
                System.out.println("index is created!");
            } 
            else {
                System.out.println("Failed to create index!");
            }
        }

		return isQuerySuccess;
	}
	
	
	public List<Repo> getAllRepos()
	{
		String fetchAllUsersCommand = "SELECT * FROM "+this.tableName;
		
		List<Repo> allRepos = new ArrayList<Repo>();
		
		Connection conn = null;
		try 
		{
			conn = this.getConnection();
			
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(fetchAllUsersCommand);
			
			System.out.println("fetchAllUsersCommand: "+fetchAllUsersCommand);
			System.out.println("fetchAllUsersCommand: "+rs.getFetchSize());

			
			while (rs.next())
			{
				String name = rs.getString("Repo_Name");
				String folder = rs.getString("Repo_Folder");
				String index = rs.getString("Repo_Index");
				String schema = rs.getString("Repo_Schema");
				
				allRepos.add(new Repo(name, folder, index, schema));
			}
		      
		    st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		
		System.out.println("fetchAllUsersCommand: allRepos: "+allRepos.size());

		return allRepos;
	}
	
	
	public void deleteUser(String repoName)
	{
		String deleteUserCommand = "";
		
		deleteUserCommand = "DELETE  FROM "+this.tableName+" where Repo_Name = '"+repoName+"'";

		
		System.out.println("deleteUser: "+deleteUserCommand);
		Connection conn = null;
		try 
		{
			conn = this.getConnection();
			
			this.executeUpdate(conn, deleteUserCommand);
		}
		catch (SQLException e) 
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Connect to the DB and do some stuff
	 */
	
}
