package com.data;
import scala.collection.mutable.ArrayBuffer;
import com.data.types.DataTable;

trait IDataConnection {
	def getColumnNames(sql: String) : ArrayBuffer[String];
	def runQuery(sql: String)       : Boolean;
	def query(sql: String)          : DataTable;
}
