package com.data.types;

class DataColumn {
	private var columnName   : String = null;
	private var columnType   : String = null;
	private var columnValue  : String = null;

	def this(name: String, value: String) {
        this();
        this.columnName = name;
        this.columnType = "";
        this.columnValue = value;
    }

	def this(name: String, t: String, value: String) {
		this(name, value);
        this.columnType = t;
	}

	def setColumnName(name: String) { this.columnName = name; }
	def setColumnType(t: String) { this.columnType = t; }
	def setColumnValue(value: String) { this.columnValue = value; }
	def getColumnName()  : String = { return this.columnName; }
	def getColumnType()  : String = { return this.columnType; }
	def getColumnValue() : String = { return this.columnValue; }
}
