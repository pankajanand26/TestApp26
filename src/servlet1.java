

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.ibm.db2.jcc.DB2SimpleDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.nosql.json.api.BasicDBList;
import com.ibm.nosql.json.api.BasicDBObject;
import com.ibm.nosql.json.util.JSON;

//import org.json.simple.JSONObject;

/**
 * Servlet implementation class servlet1
 */
@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// set defaults
	private String databaseHost = "75.126.155.153";
	private int port = 50000;
	private String databaseName = "SQLDB";
	private String user = "user08779";
	private String password = "tDI1YYrsy8xY";
	private String url = "db2://user08779:tDI1YYrsy8xY@75.126.155.153:50000/SQLDB";
	
	private boolean processVCAP(PrintWriter writer) {
		// VCAP_SERVICES is a system environment variable
		// Parse it to obtain the for DB2 connection info
		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
		writer.println("VCAP_SERVICES content: " + VCAP_SERVICES +"<br/>");

		if (VCAP_SERVICES != null) {
			// parse the VCAP JSON structure
			BasicDBObject obj = (BasicDBObject) JSON.parse(VCAP_SERVICES);
			String thekey = null;
			Set<String> keys = obj.keySet();
			writer.println("Searching through VCAP keys"+"<br/>");
			// Look for the VCAP key that holds the SQLDB information
			for (String eachkey : keys) {
				writer.println("Key is: " + eachkey);
				// Just in case the service name gets changed to lower case in the future, use toUpperCase
				if (eachkey.toUpperCase().contains("SQLDB")) {
					thekey = eachkey;
				}
			}
			if (thekey == null) {
				writer.println("Cannot find any SQLDB service in the VCAP; exiting"+"<br/>");
				return false;
			}
			BasicDBList list = (BasicDBList) obj.get(thekey);
			obj = (BasicDBObject) list.get("0");
			writer.println("Service found: " + obj.get("name")+"<br/>");
			// parse all the credentials from the vcap env variable
			obj = (BasicDBObject) obj.get("credentials");
			databaseHost = (String) obj.get("host");
			databaseName = (String) obj.get("db");
			port = (int)obj.get("port");
			user = (String) obj.get("username");
			password = (String) obj.get("password");
			url = (String) obj.get("jdbcurl");
		} else {
			writer.println("VCAP_SERVICES is null");
			return false;
		}
		writer.println("<br/>");
		writer.println("database host: " + databaseHost+"<br/>");
		writer.println("database port: " + port+"<br/>");
		writer.println("database name: " + databaseName+"<br/>");
		writer.println("username: " + user+"<br/>");
		writer.println("password: " + password+"<br/>");
		writer.println("url: " + url+"<br/>");
		return true;
	}
	
	

    /**
     * Default constructor. 
     */
    public servlet1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int yes=0,no=0;
		
		response.setContentType("text/html");
		response.setStatus(200);
		PrintWriter writer = response.getWriter();
		writer.println("IBM SQL Database, Java Demo Application using DB2 drivers"+"<br/>");
		writer.println("Servlet: " + this.getClass().getName()+"<br/>");
		writer.println("<br/>");
		writer.println("Host IP:" + InetAddress.getLocalHost().getHostAddress()+"<br/>");

		// process the VCAP env variable and set all the global connection parameters
		if (processVCAP(writer)) {
	
			// Connect to the Database
			Connection con = null;
			try {
				writer.println("<br/>");
				writer.println("Connecting to the database"+"<br/>");
				DB2SimpleDataSource dataSource = new DB2SimpleDataSource();
				dataSource.setServerName(databaseHost);
				dataSource.setPortNumber(port);
				dataSource.setDatabaseName(databaseName);
				dataSource.setUser(user);
				dataSource.setPassword (password);
				dataSource.setDriverType(4);
				con=dataSource.getConnection();
				writer.println("<br/>");
				con.setAutoCommit(false);
			} catch (SQLException e) {
				writer.println("Error connecting to database"+"<br/>");
				writer.println("SQL Exception: " + e+"<br/>");
				return;
			} 
	
			// Try out some dynamic SQL Statements
			Statement stmt = null;
			String tableName = "";
			String sqlStatement = "";
			// It is recommend NOT to use the default schema since it is correlated
			// to the generated user ID
			String schemaName = "USER08779";
			// create a unique table name to make sure we deal with our own table
			// If another version of this sample app binds to the same database, 
			// this gives us some level of isolation
			tableName = schemaName + "." + "TESTTABLE";
	
			try {
				stmt = con.createStatement();
				// Create the CREATE SCHEMA SQL statement and execute it
				sqlStatement = "CREATE SCHEMA " + schemaName;
				writer.println("Executing: " + sqlStatement+"<br/>");
				//stmt.executeUpdate(sqlStatement);
			} catch (SQLException e) {
				writer.println("Error creating schema: " + e+"<br/>");
			}
	
			// create a table
			try {
				stmt = con.createStatement();
				// Create the CREATE TABLE SQL statement and execute it
				sqlStatement = "CREATE TABLE " + tableName
						+ " (NAME VARCHAR(20), AGE INTEGER)";
				writer.println("Executing: " + sqlStatement+"<br/>");
				//stmt.executeUpdate(sqlStatement);
			} catch (SQLException e) {
				writer.println("Error creating table: " + e+"<br/>");
			}
	
			// Execute some SQL statements on the table: Insert, Select and Delete
			try {
				
//				sqlStatement = "INSERT INTO " + tableName
//						+ " VALUES (0,0)";
//				writer.println("Executing: " + sqlStatement);
//				stmt.executeUpdate(sqlStatement);
//	
				
				sqlStatement = "UPDATE " + tableName
						+ " SET YES=YES+1, NO=NO+1";
				writer.println("Executing: " + sqlStatement+"<br/>");
				stmt.executeUpdate(sqlStatement);
	
				sqlStatement = "SELECT YES,NO FROM " + tableName;
				ResultSet rs = stmt.executeQuery(sqlStatement);
				writer.println("Executing: " + sqlStatement+"<br/>");
	
				// Process the result set
				while (rs.next()) {
					yes = rs.getInt("YES");
					no = rs.getInt("NO");
					writer.println(" <h1> website visits  : " + yes + "</h1>"+"<br/>");
				}
				// Close the ResultSet
				rs.close();
	
				// Delete the record
				sqlStatement = "DELETE FROM " + tableName
						+ " WHERE NAME = \'John Smith\'";
				writer.println("Executing: " + sqlStatement+"<br/>");
				//stmt.executeUpdate(sqlStatement);
			} catch (SQLException e) {
				writer.println("Error executing:" + sqlStatement+"<br/>");
				writer.println("SQL Exception: " + e+"<br/>");
			}
	
//			// Remove the table from the database
//			try {
//				sqlStatement = "DROP TABLE " + tableName;
//				writer.println("Executing: " + sqlStatement);
//				//stmt.executeUpdate(sqlStatement);
//			} catch (SQLException e) {
//				writer.println("Error dropping table: " + e);
//			}
//			
//			// Remove the schema from the database
//			try {
//				sqlStatement = "DROP SCHEMA " + schemaName + " RESTRICT";
//				writer.println("Executing: " + sqlStatement);
//				//stmt.executeUpdate(sqlStatement);
//			} catch (SQLException e) {
//				writer.println("Error Dropping schema: " + e);
//			}
	
			// Close everything off
			try {
				// Close the Statement
				stmt.close();
				// Connection must be on a unit-of-work boundary to allow close
				con.commit();
				// Close the connection
				con.close();
				writer.println("Finished"+"<br/>");
	
			} catch (SQLException e) {
				writer.println("Error closing things off"+"<br/>");
				writer.println("SQL Exception: " + e+"<br/>");
			}
		}
		//writer.close();
		
		request.setAttribute("uname", yes);
		request.setAttribute("desc", " visits");
		request.getRequestDispatcher("/WEB-INF/websitevisits.jsp").forward(request, response);
		//response.getWriter().append("Served Servlet1 at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
