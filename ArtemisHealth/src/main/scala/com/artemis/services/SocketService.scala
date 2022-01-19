package com.artemis.services;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Collectors;

class SocketService {
	private var port    : Int = 3434;
	private var socket  : ServerSocket = null;

	private def this(port: Int) = {
		this();
		this.port = port;
	}

	def start() = {
		println(s"Attempting to open socket on port '${this.port}'");	

		try {
			this.socket = new ServerSocket(this.port, 10, InetAddress.getLoopbackAddress);
			(new Thread("Artemis Health Message Server") {
				override def run() : Unit = {
					while (!socket.isClosed) {
						var connection = socket.accept();
						if (connection != null) {
							handleConnection(connection);
							connection.close();
						}
					}
					socket.close();
				}
			}).start();
			println("Waiting...");
		} catch {
			case ex : Exception => ex.printStackTrace();
		}
	}

	def handleConnection(connection: Socket) = try {
		val reader = new BufferedReader(new InputStreamReader(connection.getInputStream, "UTF-8"));
    	val writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream, "UTF-8"));
		println(reader.lines().collect(Collectors.joining()));
	} catch {
		case ex : Exception => null;
	}
}

object SocketService {
	private var instance   : SocketService = null;

	def getInstance(port: Int) : SocketService = {
		if (instance == null) {
			instance = new SocketService(port);
		}
		return instance;
	}
}

