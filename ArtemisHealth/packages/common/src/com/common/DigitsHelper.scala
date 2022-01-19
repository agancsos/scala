package com.common;

object DigitsHelper {
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
}
