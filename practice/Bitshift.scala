object Regex {
    def main(args: Array[String]) = {
        var maxBit   = 10;
        var shift    = 2;

        for (i <- 0 to args.size - 1) {
            if (args(i) == "--max") {
                maxBit = args(i + 1).toInt;
            }
            else if (args(i) == "--shift") {
                shift = args(i + 1).toInt;
            }
        }

		for (i <- 0 to maxBit) {
			println(s"${i} = ${i << shift}");
		}
    }
}
