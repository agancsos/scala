object Regex {
	def main(args: Array[String]) = {
		var text   = "test";
    	var search = "t";

    	for (i <- 0 to args.size - 1) {
        	if (args(i) == "--text") {
            	text = args(i + 1);
        	}	 
			else if (args(i) == "--search") {
            	search = args(i + 1);
        	}
    	}
		var reg = s"${search}".r;
		var matches = reg.findAllIn(text);
    	println(s"Has Matches: ${matches.hasNext}");
   		println("Matchs:");
    	matches.foreach(m => {
        	println(s"${m}")
    	});
	}	
}
