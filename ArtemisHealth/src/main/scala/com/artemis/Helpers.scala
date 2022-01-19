package com.artemis;

object Helpers {
	def padLeft (str: String, len: Int, pad: String): String = {
		var result = "";
		if (str.length > len) {
			return str.substring(0, len);
		} else {
			for (i <- Range(str.length, len, 1)) {
				result += pad;
			}
		}
		return s"${result}${str}";
	}

	def padRight (str: String, len: Int, pad: String): String = {
        var result = "";
        if (str.length > len) {
            return str.substring(str.length - len, len);
        } else {
            for (i <- Range(str.length, len, 1)) {
                result += pad;
            }
        }
        return s"${str}${result}";
    }
}
