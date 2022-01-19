package com.common;
import scala.collection.mutable.Map;

object Common {
	def mapToJson(map: Map[String, String]): String = {
    	var result = "{";
   		map.keys.foreach(k => {
   			if (result != "") { result = s"${result},"; }
       		result = s"\"${k}\":\"${map(k)}\"";
   		});
   		result += "}";
   		return result;
 	}
}
