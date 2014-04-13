package databaseManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Query Class.
 * 
 * Creates and performs the database queries as needed.
 * 
 * @author Aaron Cheng
 *
 */
public class Query {
	
	private static Connection dbConn;
	
	/**
	 * Performs one of the INSERT, UPDATE or DELETE SQL queries.
	 * 
	 * Ideally, this should be called when we make an INSERT SQL query.
	 * Closes the Statement object after it is complete.
	 * 
	 * @pre userQuery.length > 0
	 * @param userQuery the SQL statement to be executed
	 * @return the number of rows affected by the query.
	 * @throws SQLException when the query fails
	 */
	public static int insert(String userQuery) throws SQLException
	{
		if (dbConn == null) 
		{
			dbConn = DatabaseManagement.getConnection(); 
		}
		Statement insert = dbConn.createStatement();
		int rowsAffected = insert.executeUpdate(userQuery);
		insert.close();
		return rowsAffected;
	}
	
	/**
	 * Alias of the create() method. Ideally, this is called whenever
	 * we make an UPDATE SQL query.
	 * 
	 * @pre userQuery.length > 0
	 * @param userQuery the SQL statement to be executed
	 * @return the number of rows affected by the query.
	 * @throws SQLException when the query fails
	 */
	public static int update(String userQuery) throws SQLException
	{
		return insert(userQuery);
	}
	
	
	/**
	 * Alias of the create() method. Ideally, this is called whenever
	 * we make a DELETE SQL query.
	 * @pre userQuery.length > 0
	 * @param userQuery the SQL statement to be executed
	 * @return the number of rows affected by the query.
	 * @throws SQLException when the query fails
	 */
	public static int delete(String userQuery) throws SQLException
	{
		return insert(userQuery);
	}
	
	/**
	 * Performs the general SELECT statement.
	 * 
	 * @pre userQuery.length > 0
	 * @param userQuery the SQL SELECT query
	 * @return results, the ResultSet containing the selected rows of the table.
	 * @throws SQLException is thrown if the query fails.
	 */
	public static ResultSet select(String userQuery) throws SQLException
	{
		if (dbConn == null)
		{
			dbConn = DatabaseManagement.getConnection();
		}
		
		Statement dbStatement = dbConn.createStatement();
		ResultSet results = dbStatement.executeQuery(userQuery);
		return results;
	}
	
	/**
	 * Runs a SELECT * FROM query to return every row of a specified table.
	 * 
	 * @pre userQuery.length > 0
	 * @param tableName table to run the SELECT * from
	 * @return ResultSet, the set of results for this query.
	 * @throws SQLException if query fails.
	 */
	public static ResultSet selectAll(String tableName) throws SQLException
	{
		if (dbConn == null)
		{
			dbConn = DatabaseManagement.getConnection();
		}
		
		Statement dbStatement = dbConn.createStatement();
		ResultSet results = dbStatement.executeQuery("SELECT * FROM " + tableName);
		return results;
	}
	
	public static void autoCommitOn() throws SQLException{
		if (dbConn == null)
		{
			dbConn = DatabaseManagement.getConnection();
		}
		
		
			dbConn.setAutoCommit(true);
		
	}
	
	public static void autoCommitOff() throws SQLException{
		if (dbConn == null)
		{
			dbConn = DatabaseManagement.getConnection();
		}
		
		
			dbConn.setAutoCommit(false);
		
	}
	
	
	public static void commit() throws SQLException{
		if (dbConn == null)
		{
			dbConn = DatabaseManagement.getConnection();
		}
		
		
			dbConn.commit();
		
		
	}
	
	public static void rollback() throws SQLException{
		if (dbConn == null)
		{
			dbConn = DatabaseManagement.getConnection();
		}
		
		
			dbConn.rollback();
		
		
	}
}
