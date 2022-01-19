package com.artemis.services;
import com.data._;
import com.data.types._;

class DataService {
	private var handler   : IDataConnection = null;
	
	private def this(connectionString: String, username: String, password: String) = {
		this();
		this.handler = new DataConnectionStandard(connectionString, username, password);
	}

	def serviceQuery(sql: String): DataTable = {
		return this.handler.query(sql);
	}

	def runServiceQuery(sql: String): Boolean = {
		try {
			this.handler.runQuery(sql);
		} catch {
			case _ : Throwable => return false;
		}
		return true;
	}
}

object DataService {
	private var instance   : DataService = null;
	def getInstance(connectionString: String, username: String, password: String): DataService = {
        try {
        	instance = new DataService(connectionString, username, password);
        } catch {
            case ex : Exception => ex.printStackTrace();
        }
        return instance;
    }
}
