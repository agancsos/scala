package com.artemis.models;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Map;
import com.artemis.models.IAlgorithm;
import com.artemis.models.FibonacciSequence;

object FibonacciSum extends IAlgorithm {
	override def getExpectedValue(): Any = {
		return true;
	}

	override def invoke(args: Map[String, String]): Any = {
		var maxValue = if (!args.contains("--max")) 10 else args.get("--max").get.toInt;
		var sum = 0;
		println(s"Calculating for value: '${maxValue}'");
		for (i <- Range(0, maxValue, 1)) {
			sum += FibonacciSequence.calculateFibonacci(i);
		}
		println(s"Sum: ${sum}");
		return true;
	}
}

