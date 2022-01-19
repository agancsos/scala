package com.artemis;
import com.artemis.services.ArtemisService;
import com.artemis.SR;

object Main {
	def main(args: Array[String]): Unit = {
		var session = new ArtemisService(args);
		if (args.contains("-h") || args.contains("--help")) {
			SR.helpMenu();
		} else {
			session.invoke();
		}
	}
}
