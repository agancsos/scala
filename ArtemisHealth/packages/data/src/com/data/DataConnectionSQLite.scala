package com.data;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import scala.collection.mutable.ArrayBuffer;
import java.util.List;
import com.data.types.DataTable;
import com.data.types.DataRow;
import com.data.types.DataColumn;

/*
	This class is a custom wrapper class for the SQLite JDBC driver (.jar)
	Please ensure that the SQLite .jar is in the same path as the application .jar
	Otherwise, this application will need to compile using Java class path
*/
class DataConnectionSQLite extends IDataConnection {
	private var databaseFile     : String = "";
	private var databaseHandler  : Connection = null;
	private var statementHandler : Statement = null;
  
	/*
		This method instantiates the connection to the file
	*/
	private def connect() = {
		try{
			this.databaseHandler = DriverManager.getConnection(s"jdbc:sqlite:${this.databaseFile}");
 			this.statementHandler = this.databaseHandler.createStatement();
		} catch {
			case _ : Throwable => null;
		}
	}
  
	/*
		This method disconnects from the database file
	*/
  	private def disconnect() = {
		try{
	  		this.databaseHandler.close();
		} catch {
	  		case _ : Throwable => null;
		}
  	}
  
	/*
		This method checks if the error table exists then runs a query, auditing any errors
	*/
  	def runQuery(sql: String) : Boolean = {
		this.connect();
		var result = false;
		try{
	  		this.statementHandler.executeUpdate(sql);
			result = true;
		} catch {
			case _ : Throwable => null;
		}
		this.disconnect();
		return result;
  	}
  
	/*
		This method retrieves data from the database
	*/
  	def query(sql: String) : DataTable = {
		this.connect();
		var result = new DataTable();
		try{
	  		var localResultSet = this.statementHandler.executeQuery(sql);
	  		while (localResultSet.next()){
				var row = new DataRow();
				var localResultSetMetaData = localResultSet.getMetaData();
				for(i <- Range(0, localResultSetMetaData.getColumnCount(), 1)){
					var col = new DataColumn();
					col.setColumnName(localResultSetMetaData.getColumnName(i + 1));
					col.setColumnValue(localResultSet.getString(i + 1));
					row.addColumn(col);
				}
				result.addRow(row);
	  		}
	  		localResultSet.close();
		} catch{
			case _ : Throwable => null;
		}
		this.disconnect();
		return result;
  	}
  
	/*
		This method retrieves the column names for the specified table
	*/
  	def getColumnNames(sql: String): ArrayBuffer[String] = {
		this.connect();
		var localArrayList = ArrayBuffer[String]();
		try{
	  		var localResultSet = this.statementHandler.executeQuery(sql);
	  		var localResultSetMetaData = localResultSet.getMetaData();
	  		for(i <- Range(0, localResultSetMetaData.getColumnCount(), 1)){
				localArrayList.append(localResultSetMetaData.getColumnName(i + 1).toString().toUpperCase());
	  		}
	  		localResultSet.close();
		} catch {
			case _ : Throwable => null;
		}
		this.disconnect();
		return localArrayList;
  	}

	def setDatabaseFile(path: String) = { this.databaseFile = path; }
	def getDatabaseFile() : String = { return this.databaseFile; }
}


