package com.artemis.models;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Map;

trait IAlgorithm {
	def getExpectedValue(): Any;
	def invoke(args: Map[String, String]): Any;
}

object FibonacciSequence extends IAlgorithm {
	override def getExpectedValue(): Any = {
		return true;
	}

	def calculateFibonacci(v: Int): Int = {
		if (v < 2) { return v; }
		return ((v - 2) + (v - 1));
	}

	override def invoke(args: Map[String, String]): Any = {
		var result = ArrayBuffer[Int]();
		var maxValue = if (!args.contains("--max")) 10 else args.get("--max").get.toInt;
		for (i <- Range(0, maxValue)) {
			var temp = calculateFibonacci(i);
			println(temp);
			result.append(temp);
		}
		return result == result;
	}
}

object PalindromeCheck extends IAlgorithm {
	override def getExpectedValue(): Any = {
		return true;
	}

	override def invoke(args: Map[String, String]): Any = {
		var str  : String  = if (!args.contains("--str")) "dad" else args.get("--str").get;
		println(s"Checking string: '${str}'");
		for (i <- Range(0, str.length, 1)) {
			if(str.charAt(i) != str.charAt(str.length - (1 + i))) {
				return false;
			}
		}
		return true;
	}
} 

