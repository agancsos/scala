package com.artemis.services;
import spray.json.DefaultJsonProtocol;

object JsonFormatter {
	import DefaultJsonProtocol._;

	implicit val resultJsonFormat = jsonFormat1(Result);
	implicit val resultsJsonFormat = jsonFormat1(Results);
}

