package com.data.types;
import scala.collection.mutable.ArrayBuffer;
import com.data.types.DataRow;

class DataTable {
	private var rows     : ArrayBuffer[DataRow] = ArrayBuffer[DataRow]();
	def addRow(row: DataRow) = { this.rows.append(row); }
	def getRows() : ArrayBuffer[DataRow] = { return this.rows; }
}
