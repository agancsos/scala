package com.artemis;
import com.artemis.services.{ArtemisHealthRestService, ArtemisHealthRegistry};
import akka.stream._;
import play.api._;
import play.core.server._;
import akka.actor._;
import play.api.mvc._;
import java.io.File;
import akka.actor.typed.{ActorSystem, MailboxSelector};
import akka.actor.typed.scaladsl.Behaviors;
import akka.http.scaladsl.Http;
import akka.http.scaladsl.server.Route;
import scala.util.Failure;
import scala.util.Success;

object WebApp {

	private def startServer(routes: Route, port: Int = 8081) (implicit system: ActorSystem[_]): Unit = {
		import system.executionContext;

		val futureBinding = Http().newServerAt("localhost", port).bind(routes);
            futureBinding.onComplete {
                case Success(binding) =>
                    val address = binding.localAddress;
                    system.log.info("Server online at http://{}:{}/", address.getHostString, address.getPort);
                case Failure(ex) =>
                    system.log.error("Failed to bind HTTP endpoint, terminating system", ex);
                	system.terminate();
            }
	}

	def main(port: Int = 8081): Unit = {
		println("Attempting to build server...");
		try {
			val rootBehavior = Behaviors.setup[Nothing] { context => {
				val registryActor = context.spawn(ArtemisHealthRegistry(), "ArtemisHealthRegistryActor", MailboxSelector.bounded(100));
				context.watch(registryActor);

				val routes = new ArtemisHealthRestService(registryActor)(context.system);
				startServer(routes.artemisRoutes, port)(context.system);

				Behaviors.empty;
			}};
			val system = ActorSystem.apply[Nothing](rootBehavior, "ArtemisHealthHttpServer");
		} catch {
			case ex: Exception => throw new Exception(s"Failed to start server. '${ex.getMessage()}'");
		}
	}
}
		
