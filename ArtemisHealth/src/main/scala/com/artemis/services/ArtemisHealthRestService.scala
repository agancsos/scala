package com.artemis.services;
import akka.http.scaladsl.server.Directives._;
import akka.http.scaladsl.model.StatusCodes;
import akka.http.scaladsl.server.Route;
import scala.concurrent.Future;
import com.artemis.models._;
import com.artemis.services.ArtemisHealthRegistry._;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.scaladsl.AskPattern._;
import akka.util.Timeout;
import java.time.Duration;
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._;
import com.artemis.services.JsonFormatter._;

class ArtemisHealthRestService(registry: ActorRef[ArtemisHealthRegistry.Command])(implicit val system: ActorSystem[_]) {
	private implicit val timeout = Timeout.create(Duration.ofSeconds(3000));
	
	def helloWorld(): Future[Result] = registry.ask(HelloWorld);
	def getTables(): Future[Results] = registry.ask(TablesResult);
	def getWelcome(): Future[String] = registry.ask(WelcomeMessage);
	def receiveResult(result: Result): Future[Result] = registry.ask(ReceiveResult(_, result));

	def artemisRoutes: Route = {
		pathPrefix("api") {
			concat(
				pathEnd {
					concat(
					get {
						complete(getWelcome());
					})
				},
				path(Segment) { operation =>
					concat(
					get {
						operation match {
							case "hello" => complete(helloWorld());
							case "tables" => complete(getTables());
							case whoa => complete(Result("Invalid operation..."));
						}
					},
					post {
						operation match {
							case "tables" => 
								entity(as[Result]) { r =>
									complete(receiveResult(r));
								}
							case whoa => complete(Result("Invalid operation..."));
						}
					})
				}
			)
		}
	};
}
