import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map;
import org.apache.http.impl.client.HttpAClientBuilder;

object Rest {
	def mapToJson(map: Map[String, String]): String = {
		var result = "{";
		map.keys.foreach(k => {
			if (result != "") { result = s"${result},"; }
			result = s"\"${k}\":\"${map(k)}\"";
		});
		result += "}";
		return result;
	}

	def invokeGet(url: String): String = {
		val httpClient = httpClientBuilder.create().build();
		val httpResponse = httpClient.execute(new HttpGet(url));
		val entity = httpResponse.getEntity();
		var content = "";
		if (entity != null) {
	  		val inputStream = entity.getContent();
	  		content = io.Source.fromInputStream(inputStream).getLines.mkString;
	  		inputStream.close;
		}
		httpClient.getConnectionManager().shutdown();
		return content;
  	}

	def invokePost(url: String, body: Map[String, String]): String = {
		val httpClient = httpClientBuilder.create().build();
		val bodyJson = mapToJson(body);
		val httpResponse = httpClient.execute(new HttpPOst(url, bodyJson));
		val entity = httpResponse.getEntity();
		var content = "";
		if (entity != null) {
			val inputStream = entity.getContent();
			content = io.Source.fromInputStream(inputStream).getLines.mkString;
			inputStream.close;
		}
		httpClient.getConnectionManager().shutdown();
		return content;
	}

	def main (args: Array[String]) = {
		var methodType: String				= "GET";
		var endpoint  : String				= "";
		var body	  : Map[String, String] = Map();
		var headers   : Map[String, String] = Map();

		for (i <- 0 to args.size - 1) {
			if (args(i) == "-m") {
				methodType = args(i + 1);
			} 
			else if (args(i) == "--endpoint") {
				endpoint = args(i + 1);
			} 
			else if (args(i) == "-h") {
				var pairs = args(i + 1).split(",");
				for (pair <- pairs) {
					var comps = pair.split(":");
					if (comps.size > 1) {
						headers(comps(0)) = comps(1);
					}
				}
			} 
			else if (args(i) == "-d") {
				var pairs = args(i + 1).split(",");
				for (pair <- pairs) {
					var comps = pair.split(":");
					if (comps.size > 1) {
						body(comps(0)) = comps(1);
					}
				}
			}
		}

		var rsp = "";
		methodType match {
			case "GET" => {
				rsp = invokeGet(endpoint);
			}
			case "POST" => {
				rsp = invokePost(endpoint, body);
			}
			case whoa => {
				println(s"Method (${methodType}) not valid...");
				System.exit(-1);
			}
		}
		println(s"Response: ${rsp}");	
	}
}
