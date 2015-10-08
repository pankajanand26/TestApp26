

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.db2.jcc.DB2SimpleDataSource;
import com.ibm.nosql.json.api.BasicDBList;
import com.ibm.nosql.json.api.BasicDBObject;
import com.ibm.nosql.json.util.JSON;

/**
 * Servlet implementation class detail
 */
@WebServlet("/detail")
public class detail extends HttpServlet {
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
			writer.println("IBM SQL Database, Java Demo Application using DB2 drivers"+"<br/>");
			writer.println("Servlet: " + this.getClass().getName()+"<br/>");
			writer.println("<br/>Hello");

			if (VCAP_SERVICES != null) {
				// parse the VCAP JSON structure
				BasicDBObject obj = (BasicDBObject) JSON.parse(VCAP_SERVICES);
				String thekey = null;
				Set<String> keys = obj.keySet();
				// Look for the VCAP key that holds the SQLDB information
				for (String eachkey : keys) {
					// Just in case the service name gets changed to lower case in the future, use toUpperCase
					if (eachkey.toUpperCase().contains("SQLDB")) {
						thekey = eachkey;
					}
					else{
						writer.println("<br/>The key is " + thekey);

					}
				}
				if (thekey == null) {
					writer.println("<br/>Key is null.");
					return false;
				}
				BasicDBList list = (BasicDBList) obj.get(thekey);
				obj = (BasicDBObject) list.get("0");
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
     * @see HttpServlet#HttpServlet()
     */
    public detail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ArrayList<String> choiceList =new ArrayList<String>();
		
		int qid = Integer.parseInt((String) request.getParameter("q"));
	
		int votes=0;
		String question = null;
		
		Dictionary dict = new Hashtable();
		
		response.setContentType("text/html");
		response.setStatus(200);
		PrintWriter writer = response.getWriter();
		
		// process the VCAP env variable and set all the global connection parameters
		if (processVCAP(writer)) {
	
			// Connect to the Database
			Connection con = null;
			try {
				DB2SimpleDataSource dataSource = new DB2SimpleDataSource();
				dataSource.setServerName(databaseHost);
				dataSource.setPortNumber(port);
				dataSource.setDatabaseName(databaseName);
				dataSource.setUser(user);
				dataSource.setPassword (password);
				dataSource.setDriverType(4);
				con=dataSource.getConnection();
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
			tableName = schemaName + "." + "POLL_CHOICES";
	
			// Execute some SQL statements on the table: Insert, Select and Delete
	
			try {
				
			stmt = con.createStatement();
								
				sqlStatement = "SELECT OPTION,VOTES FROM " + tableName + " WHERE \"Q_ID\" = "+ qid +" ORDER BY VOTES DESC";
				writer.println("sqlStatement : " + sqlStatement);
				ResultSet rs = stmt.executeQuery(sqlStatement);
				if(rs==null){
					writer.println("rs not emppty");
				}
			
				// Process the result set
				while (rs.next()) {
					writer.println("Rowset not empty." );
					
					votes= rs.getInt("VOTES");
					question= rs.getString("OPTION");
					choiceList.add(question);
					dict.put(question, votes);
			}
				// Close the ResultSet
				rs.close();
	
				stmt = con.createStatement();
				
				sqlStatement = "SELECT OPTION FROM " + "USER08779.POLL_QUESTION" + " WHERE \"Q_ID\" = "+ qid;
				writer.println("sqlStatement : " + sqlStatement);
				ResultSet rs1 = stmt.executeQuery(sqlStatement);
				if(rs1==null){
					writer.println("rs not emppty");
				}
			
				// Process the result set
				while (rs1.next()) {
					writer.println("Rowset not empty." );
					
					question= rs1.getString("OPTION");
										
			}
				// Close the ResultSet
				rs1.close();
	
				
			} catch (SQLException e) {
				writer.println("Error connecting to database"+"<br/>");
				writer.println("SQL Exception: " + e+"<br/>");
				
			}
	
	
			// Close everything off
			try {
				// Close the Statement
				stmt.close();
				// Connection must be on a unit-of-work boundary to allow close
				//con.commit();
				// Close the connection
				//con.close();
	
			} catch (SQLException e) {
				writer.println("Error connecting to database"+"<br/>");
				writer.println("SQL Exception: " + e+"<br/>");
				
			}
		}
		
//		writer.close();
					//request.setAttribute("list", questionList);
		request.setAttribute("list", choiceList);
		request.setAttribute("qid", qid);
		request.setAttribute("ques", question);
		request.setAttribute("dict", dict);
		request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);			
				//response.getWriter().append("Served at: ").append(request.getContextPath());
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		int qid = Integer.parseInt((String) request.getParameter("q"));
	
		String option= request.getParameter("option");
		
		PrintWriter writer = response.getWriter();
		
		// process the VCAP env variable and set all the global connection parameters
		if (processVCAP(writer)) {
	
			// Connect to the Database
			Connection con = null;
			try {
				DB2SimpleDataSource dataSource = new DB2SimpleDataSource();
				dataSource.setServerName(databaseHost);
				dataSource.setPortNumber(port);
				dataSource.setDatabaseName(databaseName);
				dataSource.setUser(user);
				dataSource.setPassword (password);
				dataSource.setDriverType(4);
				con=dataSource.getConnection();
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
			tableName = schemaName + "." + "POLL_CHOICES";
	
			// Execute some SQL statements on the table: Insert, Select and Delete

			try {
				
				stmt = con.createStatement();
									
					sqlStatement = "UPDATE " + tableName +	" SET VOTES = VOTES + 1 WHERE \"OPTION\" = '" + option + "' AND \"Q_ID\" = '"+ qid +"'";
					writer.println("sqlStatement : " + sqlStatement);
					stmt.executeUpdate(sqlStatement);
					
				} catch (SQLException e) {
					writer.println("Error connecting to database"+"<br/>");
					writer.println("SQL Exception: " + e+"<br/>");
					
				}
	
			// Close everything off
			try {
				// Close the Statement
				stmt.close();
				// Connection must be on a unit-of-work boundary to allow close
				con.commit();
				// Close the connection
				con.close();
	
			} catch (SQLException e) {
				writer.println("Error connecting to database"+"<br/>");
				writer.println("SQL Exception: " + e+"<br/>");
				
			}
			
			doGet(request,response);
	
		}
	}
}
