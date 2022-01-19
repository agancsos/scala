package com.artemis.services;
import com.artemis.services.DataService;
import com.artemis.services.SocketService;
import com.artemis.services.AlgorithmService;
import com.artemis.models.IAlgorithm;
import scala.collection.mutable.Map;
import com.artemis.WebApp;

class ArtemisService {
	private var operation      : String = "dummy";
	private var username       : String = "";
	private var password       : String = "";
	private var database       : String = "";
	private var host           : String = "";
	private var socketPort     : Integer = 3434;
	private var algorithm      : String = "fibonacci";
	private var dataService    : DataService = null;
	private var socketService  : SocketService = null;
	private var algorithmArgs  : Map[String, String]   = Map[String, String]();

	def this(args: Array[String]) = {
		this();
		for (i <- Range(0, args.length, 1)) {
			args(i) match {
				case "--user"        => this.username = args(i + 1);
				case "--pass"        => this.password = args(i + 1);
				case "--db"          => this.database = args(i + 1);
				case "--op"          => this.operation = args(i + 1);
				case "--host"        => this.host = args(i + 1);
				case "--socket-port" => this.socketPort = args(i + 1).toInt;
				case "--alg"         => this.algorithm = args(i + 1);
				case whoa            => {
					try {
						this.algorithmArgs.put(args(i), args(i + 1));
					} catch {
						case _: Throwable => null;
					}
				}
			} 
		}
		this.dataService = DataService.getInstance(s"jdbc:postgresql://${this.host}/${this.database}", this.username, this.password); 
	}

	def invoke()  = {
		this.operation match {
			case "dummy"           => println("Welcome...");
			case "basic-db"        => {
				if (this.dataService != null) {
					var rows = this.dataService.serviceQuery("SELECT tablename FROM pg_tables").getRows();
					for (row <- rows) {
						println(s"${row.getColumn("tablename").getColumnValue()}");
					}
				} else {
					println("Failed to initialize data service...");
				}
			};
			case "basic-socket"    => {
				this.socketService = SocketService.getInstance(this.socketPort);
				this.socketService.start();
			};
			case "basic-rest"      => WebApp.main(this.socketPort);
			case "alg"             => println(AlgorithmService.invokeAlgorithm(this.algorithm, this.algorithmArgs));
			case whoa              => println(s"Invalid operation (${this.operation})...");
		}
	}
}
