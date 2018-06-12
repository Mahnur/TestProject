package com.websystique.springmvc.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
public class UserManagementDB {

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
	private final String tableName = "USER_INFO";
	
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
	public void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		// Create a table
		try {
		    String createString =
			        "CREATE TABLE " + this.tableName + " ( " +
			        "ID INTEGER NOT NULL, " +
			        "NAME varchar(40) NOT NULL, " +
			        "STREET varchar(40) NOT NULL, " +
			        "CITY varchar(20) NOT NULL, " +
			        "STATE char(2) NOT NULL, " +
			        "ZIP char(5), " +
			        "PRIMARY KEY (ID))";
			this.executeUpdate(conn, createString);
			System.out.println("Created a table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
		
		// Drop the table
		try {
		    String dropString = "DROP TABLE " + this.tableName;
			this.executeUpdate(conn, dropString);
			System.out.println("Dropped the table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}
	}
	
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
				        "User_ID varchar(15) NOT NULL, " +
				        "User_Name varchar(40) NOT NULL, " +
				        "User_Role varchar(25) NOT NULL, " +
				        "User_Email varchar(30) NOT NULL, " +
				        "User_Password varchar(30) NOT NULL UNIQUE, " +
				        "PRIMARY KEY (User_ID))";
				
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
	
	public boolean createUser(User newUser)
	{
		boolean isQuerySuccess = true;
		
		String addNewUserCommand = "";
		
		addNewUserCommand = "INSERT INTO "+this.tableName+" (User_ID, User_Name,User_Role,User_Email,User_Password) ";
		addNewUserCommand = addNewUserCommand + "VALUES ('"+newUser.getId()+"','";
		addNewUserCommand = addNewUserCommand + newUser.getUsername()+"','";
		addNewUserCommand = addNewUserCommand + newUser.getRole()+"','";
		addNewUserCommand = addNewUserCommand + newUser.getEmail()+"','";
		addNewUserCommand = addNewUserCommand + newUser.getPassword()+"')";
		
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

		return isQuerySuccess;
	}
	
	public List<User> getAllUsers()
	{
		String fetchAllUsersCommand = "SELECT * FROM "+this.tableName;
		
		List<User> allUsers = new ArrayList<User>();
		
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
				String id = rs.getString("User_ID");
				String name = rs.getString("User_Name");
				String role = rs.getString("User_Role");
				String email = rs.getString("User_Email");
				String password = rs.getString("User_Password");
				
				allUsers.add(new User(id, name, role, email, password));
			}
		      
		    st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		
		System.out.println("fetchAllUsersCommand: allUsers: "+allUsers.size());

		return allUsers;
	}
	
	public boolean updateUser(User newUser)
	{
		boolean isUserUpdated = true;
		String updateUserCommand = "";
		
		updateUserCommand = "UPDATE "+this.tableName+"  ";
		updateUserCommand = updateUserCommand + "SET User_Name = '" + newUser.getUsername()+"',";
		updateUserCommand = updateUserCommand + " User_Role = '" + newUser.getRole()+"',";
		updateUserCommand = updateUserCommand + " User_Email = '" + newUser.getEmail()+"',";
		updateUserCommand = updateUserCommand + " User_Password = '" + newUser.getPassword()+"'";
		updateUserCommand = updateUserCommand + " where User_ID = "+newUser.getId();
		
		System.out.println("updateUser: "+updateUserCommand);
		Connection conn = null;
		try 
		{
			conn = this.getConnection();
			
			this.executeUpdate(conn, updateUserCommand);
		}
		catch (SQLException e) 
		{
			isUserUpdated= false;
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		
		return isUserUpdated;
	}
	
	public void deleteUser(String userID)
	{
		String deleteUserCommand = "";
		
		deleteUserCommand = "DELETE  FROM "+this.tableName+" where User_ID = "+userID;

		
		System.out.println("updateUser: "+deleteUserCommand);
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
	
	public boolean isUserExist(User loginUser)
	{
		boolean isUserExist = false;
		
		String checkUserCommand = "SELECT * FROM "+this.tableName+" where User_Name = '"+loginUser.getUsername()+"'";

		Connection conn = null;
		try 
		{
			conn = this.getConnection();
			
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(checkUserCommand);
			
			System.out.println("checkUserCommand: "+checkUserCommand);
			System.out.println("checkUserCommand: "+rs.getFetchSize());

			
			while (rs.next())
			{
				isUserExist = true;
				
				break;
			}
		      
		    st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		
		return isUserExist;
	}
	
	public String getUserLoginPage(User loginUser)
	{
		String loginPage = "user_error";
		String checkLoginCommand = "SELECT * FROM "+this.tableName+" where User_Name = '"+loginUser.getUsername();
		checkLoginCommand = checkLoginCommand +"' && User_Password = '"+loginUser.getPassword()+"'";

		Connection conn = null;
		try 
		{
			conn = this.getConnection();
			
			Statement st = conn.createStatement();
			
			System.out.println("checkLoginCommand: "+checkLoginCommand);

			ResultSet rs = st.executeQuery(checkLoginCommand);
			
			System.out.println("checkLoginCommand: "+rs.getFetchSize());

			
			while (rs.next())
			{
				String role = rs.getString("User_Role");
				
				if(role.equalsIgnoreCase("admin"))
					loginPage = "admin";
				else
					loginPage = "userPage";
				
				break;
			}
		      
		    st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		
		if(loginPage.equalsIgnoreCase("user_error"))
		{
			if(isUserExist(loginUser))
				loginPage = "password_error";
		}
		
		return loginPage;
	}
	
	/**
	 * Connect to the DB and do some stuff
	 */
	
}
