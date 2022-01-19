package com.artemis.services;
import akka.actor.typed.ActorRef;
import com.artemis.services.DataService;
import akka.actor.typed.Behavior;
import com.typesafe.config.{Config, ConfigFactory};
import akka.actor.typed.scaladsl.Behaviors;
import scala.collection.immutable;
import scala.collection.mutable;
import scala.io.Source;

final case class Result(result: String);
final case class Results(results: immutable.Seq[Result]);

object ArtemisHealthRegistry {
	sealed trait Command;
	var config = ConfigFactory.load();
	var host = config.getString("artemishealth.rest.db.host");
	var database = config.getString("artemishealth.rest.db.database");
	var username = config.getString("artemishealth.rest.db.username");
	var password = config.getString("artemishealth.rest.db.password");
	var service = DataService.getInstance(s"jdbc:postgresql://${host}/${database}", username, password);
	final case class HelloWorld(replyTo: ActorRef[Result]) extends Command;
	final case class TablesResult(replyTo: ActorRef[Results]) extends Command;
	final case class WelcomeMessage(replyTo: ActorRef[String]) extends Command;
	final case class ReceiveResult(replyTo: ActorRef[Result], result: Result) extends Command;
	
	def apply(): Behavior[Command] = registry()

	private def registry(): Behavior[Command] =
		Behaviors.receiveMessage {
			case HelloWorld(replyTo) =>
				replyTo ! Result("HELLO WORLD");
				Behaviors.same;
			case WelcomeMessage(replyTo) =>
				var lines = Source.fromFile("README.md").getLines.toList;
				var text = "";
				for (line <- lines) {
					text += s"${line}\n";
				}
				replyTo ! text;
				Behaviors.same;
			case TablesResult(replyTo) =>
				if (service != null) {
					var temp : mutable.ListBuffer[Result] = mutable.ListBuffer[Result]();
					var rows = this.service.serviceQuery("SELECT tablename FROM pg_tables").getRows();
					for (i <- rows) {
						temp.append(Result(i.getColumn("tablename").getColumnValue()));
					}
					replyTo ! Results(temp.toList);
				} else {
					replyTo ! Results(Array[Result](Result("DataService failed to initialize...")));
				}
				Behaviors.same;
			case ReceiveResult(replyTo, result) =>
				replyTo ! Result(s"Received ${result.result}.  No action performed.");
				Behaviors.same;
    	};
}

