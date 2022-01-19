package com.data.types;
import scala.collection.mutable.ArrayBuffer;
import com.data.types.DataColumn;

class DataRow {
	private var columns : ArrayBuffer[DataColumn] = ArrayBuffer[DataColumn]();
	
	def this(columns: ArrayBuffer[DataColumn]) {
		this();
		this.columns = columns;
	}

	def contains(name: String) : Boolean = {
		for (column <- this.columns) {
			if (column.getColumnName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	def addColumn(column: DataColumn) = {
		if (!this.contains(column.getColumnName())) {
			this.columns.append(column);
		}
	}

	def getColumn(name: String) : DataColumn = {
		for (column <- this.columns) {
            if (column.getColumnName().equals(name)) {
                return column;
            }
        }
		return new DataColumn();
	}	

	def getColumns() : ArrayBuffer[DataColumn] = { return this.columns; }
}
