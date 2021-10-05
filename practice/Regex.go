package main
import (
    "os"
    "fmt"
    "regexp"
)

func main() {
    var text   = "test";
    var search = "t";

	for i := 0; i < len(os.Args); i++ {
		if os.Args[i] == "--text" {
			text = os.Args[i + 1];
		} else if os.Args[i] == "--search" {
			search = os.Args[i + 1];
		}
	}

    match, _ := regexp.MatchString(search, text);
    fmt.Printf("Has Matches: %v\n", match);
	fmt.Printf("Matchs:\n");
	reg, err := regexp.Compile(search);
	if err != nil {
		fmt.Printf("Failed to compile regex...\n");
		os.Exit(-1);
	}
	matches := reg.FindAllStringIndex(text, -1);
	for _, m := range matches {
		fmt.Printf("%v (%v)\n", string(text[m[0]:m[1]]), m);
	}
    os.Exit(0);
}
