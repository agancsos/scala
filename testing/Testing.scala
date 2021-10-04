import java.util.Map;

object Testing {
	def main(args: Array[String]) = {
		var fullName      : String = "";

		// Parse arguments
		for (i <- 0 to args.size - 1) {
			if (args(i) == "--name") {
				fullName = args(i + 1);
			}
		}

		// Basic print test
		println("TEST");

		// Conditional print test
		if (fullName != "") {
			println(String.format("You are %s", fullName));
		}


		// Environment test
		println(String.format("%s; %s", System.getProperty("os.name"), System.getProperty("os.version")));

		// Iterator test
		System.getenv().entrySet().forEach(pair => {
			println(String.format("%s = %s", pair.getKey(), pair.getValue()))
		});

		// Function test
		println(String.format("10 + 5 = %.2f", applyOperator(10, 5, "+")));

		println("Tests passed!");

	}

	def applyOperator(left: Double, right: Double, operator: String): Double = {

		// Switch-case test
		operator match {
			case "+" => left + right;
			case whoa => return 0;
		}
	}
}
