package com.artemis.services;
import com.artemis.models._;
import scala.collection.mutable.Map;

class AlgorithmService {
}

object AlgorithmService {
	def invoke(alg: IAlgorithm, args: Map[String, String]): Boolean = {
		try {
			if (alg.invoke(args) == alg.getExpectedValue()) {
				return true;
			} else {
				return false;
			}
		} catch {
			case ex: Exception => {
				ex.printStackTrace();
				return false;
			}
		}
		return false;
	}

	def invokeAlgorithm(name: String, args: Map[String, String]): Boolean = {
		name match {
			case "fibonacci"      => return invoke(FibonacciSequence, args);
			case "palindrome"     => return invoke(PalindromeCheck, args);
			case "fibonacci-sum"  => return invoke(FibonacciSum, args);
			case whoa => {
				println(s"Invalid algorithm (${name})");
				return false;
			}
		}
	}
}


