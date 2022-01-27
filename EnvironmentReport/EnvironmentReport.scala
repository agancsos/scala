import scala.collection.JavaConverters._

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

object EnvironmentReport {
    def main(args: Array[String]): Unit = {
        var envs  = System.getenv().asScala;
        var props = System.getProperties().asScala;
        var name  = "";
        if (args.contains("--var")) {
             name = args(args.indexOf("--var") + 1);
        }
        if (!args.contains("--no-logo")) {
            println(Helpers.padRight("", 80, "#"));
            println(Helpers.padRight("# Name          : EnvironmentReport", 79, " ") + "#");
            println(Helpers.padRight("# Author        : Abel Gancsos", 79, " ") + "#");
            println(Helpers.padRight("# Version       : 1.0.0.0", 79, " ") + "#");
            println(Helpers.padRight("", 80, "#"));
        }
        if (name != "") {
            if (System.getenv(name) != null) {
                println(s"${Helpers.padRight(name, 30, " ")} : ${System.getenv(name)}");
            }
            if (System.getProperties().get(name) != null) {
                println(s"${Helpers.padRight(name, 30, " ")} : ${System.getProperties().get(name)}");
            }
        } else {
            for ((k, v) <- envs) {
                println(s"${Helpers.padRight(k, 30, " ")} : ${v}");
            }
            println(Helpers.padRight("", 80, "-"));
            for ((k, v) <- props) {
                if (name == k || name == "") {
                    println(s"${Helpers.padRight(k, 30, " ")} : ${v}");
                }
            }
        }
    }
}
