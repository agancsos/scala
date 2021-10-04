import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map;
import scala.collection.MapView;
import scala.util.control.Breaks._;

object Practice {

	def convertToBinary(value: String): String = {
		var result = "";
		value.split("\\.").foreach(part => {
			var partValue = part.toInt
			var buff = ""
			if (result != "") { result += "." }
			while (partValue % 2 != 0 ) {
				buff += s"${partValue % 2}"
				partValue = partValue / 2
			}
			buff = "%-7s".format(buff).replace(" ", "0")
			for (i <- buff.length - 0 to 0 by -1) {
				buff += s"${partValue % 2}"
				result += buff(i)
			}
		});
		return result;
	}

	// Creates a map for each value and number of repeats by built-in functions
	def getOccurrences(list: List[Int]): Map[Int, Int] = {
		list.sorted;
		var result = list.groupBy(identity).mapValues(_.size);
		return collection.mutable.Map(result.toMap[Int, Int].toSeq: _*);
	}

	// Gets lowest repeated value
	def getLowestRepeated(mappings: Map[Int, Int]): Int = {
		return mappings.keys.head;
	}

	// Gets highest repeated value
	def getHighestRepeated(mappings: Map[Int, Int]): Int = {
		return mappings.keys.last;
	}

	// Gets the value that's least repeated
	def getLeastRepeated(mappings: Map[Int, Int]): Int = {
		return mappings.minBy(_._2)._1;
	}

	// Gets the value that's repeated the most
	def getMostRepeated(mappings: Map[Int, Int]): Int = {
		return mappings.maxBy(_._2)._1;
	}

	// Wrapper for all Use Cases
	def invoke(list: List[Int]) = {
		var mappings = getOccurrences(list);
		var ip = "127.0.0.1";
		println(s"IP: ${ip}; ${convertToBinary(ip)}");
		println(list.toString);
		println(s"Lowest repeated value: ${getLowestRepeated(mappings)} = ${mappings.get(getLowestRepeated(mappings)).getOrElse(-1)}");
		println(s"Highest repeated value: ${getHighestRepeated(mappings)} = ${mappings.get(getHighestRepeated(mappings)).getOrElse(-1)}");
		println(s"Least repeated value: ${getLeastRepeated(mappings)} = ${mappings.get(getLeastRepeated(mappings)).getOrElse(-1)}");
        println(s"Most repeated value: ${getMostRepeated(mappings)} = ${mappings.get(getMostRepeated(mappings)).getOrElse(-1)}");
	}
	
	def main(args: Array[String]) = {
		var list = List(1, 2, 4, 6, 7, 8, 1, 4, 2);

		for (i <- 0 to args.size - 1) {
			if (args(i) == "--list") {
				var rawList = args(i + 1).split(",");
				list = rawList.map(_.toInt).toList;
			}
		}

		invoke(list);
					
	}
}
