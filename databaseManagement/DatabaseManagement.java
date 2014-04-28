package databaseManagement;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import com.mysql.jdbc.Driver;

/**
 * DatabaseManagement class
 * 
 * Connects to a local MySQL database using JDBC.
 * @author Aaron Cheng
 *
 */
public class DatabaseManagement 
{

	/**
	 * Static DatabaseManagement instance.
	 */
	private static DatabaseManagement instance;

	/**
	 * Connection to the database.
	 * 
	 * Only a single connection initiated per instance of CRS.
	 */
	private static Connection dbCon;

	/**
	 * Private constructor, we only need to have a SINGLE instance
	 * of this class
	 * Registers the driver to the current MySQL host.
	 */
	private DatabaseManagement() throws SQLException
	{
		DriverManager.registerDriver(new Driver()); //register the driver class
		//create the connection to the database.
		dbCon = DriverManager.getConnection("jdbc:mysql://www.sourcesixsoftware.com/crs_database", "crsAdmin", 
				"e=mc^2");
	}

	/**
	 * Returns the current instance of the DatabaseManagement class.
	 * 
	 * Only a single thread allowed access at any given time.
	 * 
	 * @return The current instance of the DatabaseMangement class
	 * @throws SQLException if the Database management class cannot be instantiated.
	 */
	public static synchronized DatabaseManagement getInstance() throws SQLException
	{
		if (instance == null)
		{
			instance = new DatabaseManagement();
		}
		return instance;
	}

	/**
	 * Returns the database connection. 
	 * 
	 * Also creates the DatabaseManagement class if no instance of the 
	 * class exists yet.
	 *  
	 * @return The current connection object.
	 * @throws SQLException if database cannot be found, or connection cannot be started.
	 */
	public static synchronized Connection getConnection() throws SQLException
	{
		instance = getInstance(); 

		return dbCon;
	}


}
