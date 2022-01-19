package com.artemis;
import com.artemis.Helpers;

object SR {
	var applicationName        = "Artemis Health";
	var applicationAuthor      = "Abel Gancsos";
	var applicationVersion     = "1.0.0.0";
	var applicationDescription = "Small tool to help prepare for the Artemis Health interview.";
	var applicationFlags   = Map(
		"-h|--help"     -> "Prints help menu",
		"--op"          -> "Operation to run",
		"--user"        -> "Username for user to use for PostgreSQL connection",
		"--pass"        -> "Password for user to use for PostgreSQL connection",
		"--db"          -> "Name of the PostgreSQL to connect to",
		"--host"        -> "Target host for the operation",
		"--socket-port" -> "Port to use when creating the socket.  Default is 3434",
		"--alg"         -> "Name of the algorithm to run.  Default is fibonacci"
	);
	var applicationOps    = Map(
		"dummy"        -> "Simply prints a welcome message",
		"basic-db"     -> "Connects to a local PostgreSQL database and lists user tables",
		"basic-socket" -> "Opens a socket and prints out incoming messages",
		"alg"          -> "Runs the specified algorithm",
		"basic-rest"   -> "Starts a simple REST service"
	);
	var algorithms        = Map(
		"fibonaccci"   -> Map(
			"description"    -> "Fibonacci sequence",
			"options"        -> Map[String, String](
				"--max" -> "Max number of elements to print in the fibonacci sequence"
			)
		),
		"palindrome"   -> Map(
			"description"    -> "Checks if a string is a palindrome",
			"options"        -> Map[String, String](
				"--str" -> "String to check"
			)
		),
		"fibonaccci-sum"   -> Map(
            "description"    -> "Sum of a fibonacci sequence",
            "options"        -> Map[String, String](
                "--max" -> "Max number of elements to print in the fibonacci sequence"
            )
        )
	);

	def helpMenu() = {
		println(Helpers.padRight("", 80, "#"));
		println(Helpers.padRight(s"# Name        : ${applicationName}", 79, " ") + "#");
		println(Helpers.padRight(s"# Version     : ${applicationVersion}", 79, " ") + "#");
		println(Helpers.padRight(s"# Author      : ${applicationAuthor}", 79, " ") + "#");
		println(Helpers.padRight(s"# Description : ${applicationDescription}", 79, " ") + "#");
		println(Helpers.padRight(s"# Flags       :", 79, " ") + "#");
		for ((k, v) <- applicationFlags) {
			println(Helpers.padRight(s"#   ${k} : ${v}", 79, " ") + "#");
		}
		println(Helpers.padRight(s"# Operations :", 79, " ") + "#");
        for ((k, v) <- applicationOps) {
            println(Helpers.padRight(s"#   ${k} : ${v}", 79, " ") + "#");
        }
		println(Helpers.padRight(s"# Algorithms :", 79, " ") + "#");
		for ((k, v) <- algorithms) {
			println(Helpers.padRight(s"#   ${k} : ${v.get("description").get}", 79, " ") + "#");
			for ((k2, v2) <- v.get("options").get.asInstanceOf[Map[String, String]]) {
				println(Helpers.padRight(s"#     ${k2} : ${v2}", 79, " ") + "#");
			}
		}
		println(Helpers.padRight("", 80, "#"));
	}
}
