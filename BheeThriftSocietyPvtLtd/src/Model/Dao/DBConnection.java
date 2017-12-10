package Model.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
			//static reference to itself
	 private static DBConnection instance = new DBConnection();
	 public static final String URL = "jdbc:postgresql://localhost:5433/bheethriftsociety";
	 public static final String USER = "postgres";
	 public static final String PASSWORD = "password";
	 public static final String DRIVER_CLASS = "org.postgresql.Driver";
	    
	  //private constructor
	    private DBConnection() {
	        try {
	            Class.forName(DRIVER_CLASS);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private Connection createConnection() {
	        Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            System.out.println("ERROR: Unable to Connect to Database.");
	            e.printStackTrace();
	        }
	        return connection;
	    }   
	    
	    public static void close(Connection connection) {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                /*log or print or ignore*/
	            }
	        }
	    }
	 
	    public static void close(PreparedStatement statement) {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                /*log or print or ignore*/
	            }
	        }
	    }
	 
	    public static void close(ResultSet resultSet) {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                /*log or print or ignore*/
	            }
	        }
	    }
	    
	    public static Connection getConnection() {
	        return instance.createConnection();
	    }
	    
	    
	    
}


