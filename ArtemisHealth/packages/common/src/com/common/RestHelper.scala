package com.common;
import org.apache.http.impl.client._;
import org.apache.http.client.methods._;
import scala.collection.mutable.Map;

object RestHelper {
	def invokeGet(url: String): String = {
        val httpClient = HttpClientBuilder.create().build();
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
        val httpClient = HttpClientBuilder.create().build();
        val bodyJson = com.common.Common.mapToJson(body);
        val httpResponse = httpClient.execute(new HttpPost(url));
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
}
