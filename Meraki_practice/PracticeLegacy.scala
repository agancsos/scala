import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map;
import scala.collection.MapView;

object Practice {

	def getUnique(list: List[Int]): List[Int] = {
		var result = new ListBuffer[Int]();
		for (i <- list) {
			if (!result.contains(i)) {
				result += i;
			}
		}
		result.sorted;
		return result.toList;
	}

	def occurrences(list: List[Int], value: Int): Int = {
		var result = 0;
		for (i <- list) {
			if (i == value) { result += 1; }
		}
		return result;
	}

	def getOccurrences(list: List[Int]): Map[Int, Int] = {
		var result = scala.collection.mutable.Map[Int, Int]();
		getUnique(list).foreach(elem => {
			result.addOne(elem, occurrences(list, elem))
		});
		return collection.mutable.Map(result.toSeq: _*);
	}

	// Gets lowest repeated value
	def getLowestRepeated(mappings: Map[Int, Int]): Int = {
		return mappings.keys.head;
	}

	// Gets highest repeated value
	def getHighestRepeated(mappings: Map[Int, Int]): Int = {
		return mappings.keys.last;
	}

	// Wrapper for all Use Cases
	def invoke(list: List[Int]) = {
		var mappings = getOccurrences(list);
		println(list.toString);
		println(s"Lowest repeated value: ${getLowestRepeated(mappings)} = ${mappings.get(getLowestRepeated(mappings)).getOrElse(-1)}");
		println(s"Highest repeated value: ${getHighestRepeated(mappings)} = ${mappings.get(getHighestRepeated(mappings)).getOrElse(-1)}");
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
