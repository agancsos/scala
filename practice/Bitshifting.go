package main
import (
	"fmt"
	"os"
	"strconv"
)

type BitShifter struct {
	maxBit   int
	shift    int
}

func (x BitShifter) Invoke() {
	for i := 0; i < x.maxBit; i++ {
        fmt.Printf("%d = %v\n", i,  i << x.shift);
    }
}

func (x BitShifter)  MaxBit() int { return x.maxBit; }
func (x BitShifter)  Shift() int { return x.shift; }
func (x *BitShifter) SetMaxBit(max int) { x.maxBit = max; }
func (x *BitShifter) SetShift(shift int) { x.shift = shift; }

func main() {
	var maxBit = 10;
	var shift  = 2;
	var session = &BitShifter{};
	var err error;

	for i := 0; i < len(os.Args); i++ {
		if os.Args[i] == "--max" {
			maxBit, err = strconv.Atoi(os.Args[i + 1]);
			if err != nil {
				fmt.Printf("Failed to convert parameter %v\n", os.Args[i + 1]);
				os.Exit(-1);
			}
		} else if os.Args[i] == "--shift" {
			shift, err = strconv.Atoi(os.Args[i + 1]);
			if err != nil {
				fmt.Printf("Failed to convert parameter %v\n", os.Args[i + 1]);
				os.Exit(-2);
			}
		}
	}

	session.SetMaxBit(maxBit);
	session.SetShift(shift);
	session.Invoke();
	os.Exit(0);
}
