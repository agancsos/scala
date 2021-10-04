package main
import (
	"fmt"
	"os"
	"sort"
	"strings"
	"strconv"
)

// IntKeyPair and IntKeyPairCollection
// This is needed as the Sort function doesn't handle Map elements
type IntKeyPair struct {
	Key       int
	Value     int
}

type IntKeyPairCollection []IntKeyPair;
func (x IntKeyPairCollection) Len() int { return len(x); }
func (x IntKeyPairCollection) Less(i int, j int) bool { return x[i].Value < x[j].Value; }
func (x IntKeyPairCollection) Swap(i int, j int) { x[i], x[j] = x[j], x[i]; }
/******************************************************************************/

// Practice
// This is used as the wrapping class
type practice struct {
	list         []int
	mapping      map[int]int
	sortedPairs  IntKeyPairCollection
	sortedKeys   IntKeyPairCollection
}

func NewPractice(list []int) *practice {
	var instance = &practice{};
	instance.list = list;
	instance.mapping = map[int]int{};
	for _, value := range instance.list {
		instance.mapping[value] = instance.mapping[value] + 1;
	}
	for key, value := range instance.mapping {
		instance.sortedPairs = append(instance.sortedPairs, IntKeyPair{key, value});
		instance.sortedKeys = append(instance.sortedKeys, IntKeyPair{0, key});
	}
	sort.Sort(instance.sortedPairs);
	sort.Sort(instance.sortedKeys);
	return instance;
}

func (x practice) ConvertToBinary(value string) string {
	var result = "";
	for _, part := range strings.Split(value, ".") {
		var buff = "";
		if result != "" { result += "."; }
		partValue, err := strconv.Atoi(part);
		if err != nil {
			fmt.Printf("Failed to convert %v\n", part);
			return fmt.Sprintf("%s (Incomplete)", result);
		}
		for ;partValue % 2 != 0; {
			buff = fmt.Sprintf("%s%s", buff, strconv.Itoa(partValue % 2));
			partValue = partValue / 2;
		}
		buff = strings.Replace(fmt.Sprintf("%-8s", buff), " ", "0", -1);
		for i := len(buff) - 1; i >= 0; i-- { result += string(buff[i]); }
	}
	return result;
}

func (x practice) Invoke() {
	var ip = "127.0.0.1";
	fmt.Printf("IP: %s;%s\n", ip, x.ConvertToBinary(ip));
	fmt.Printf("%v\n", x.mapping);
	fmt.Printf("Lowest repeated value: %d = %d\n", x.GetLowestRepeated(), x.mapping[x.GetLowestRepeated()]);
	fmt.Printf("Highest repeated value: %d = %d\n", x.GetHighestRepeated(), x.mapping[x.GetHighestRepeated()]);
	fmt.Printf("Least repeated value: %d = %d\n", x.GetLeastRepeated(), x.mapping[x.GetLeastRepeated()]);
	fmt.Printf("Most repeated value: %d = %d\n", x.GetMostRepeated(), x.mapping[x.GetMostRepeated()]);
}
func (x practice) GetLowestRepeated() int { return x.sortedKeys[0].Value; }
func (x practice) GetHighestRepeated() int { return x.sortedKeys[len(x.sortedPairs) - 1].Value; }
func (x practice) GetLeastRepeated() int { return x.sortedPairs[0].Key; }
func (x practice) GetMostRepeated() int { return x.sortedPairs[len(x.sortedPairs) - 1].Key; }
/******************************************************************************/

func main() {
	var list = []int {1, 2, 4, 6, 7, 8, 1, 4, 2};

	for i := 0; i < len(os.Args); i++ {
		if os.Args[i] == "--list" {
			var rawList = strings.Split(os.Args[i + 1], ",");
			if len(rawList) > 0 {
				list = []int {};
				for elem := range rawList {
					value, err := strconv.Atoi(rawList[elem]);
					if err == nil {
						list = append(list, value);
					} else {
						fmt.Println("Failed to parse: %v\n", elem);
						os.Exit(-1);
					}
				}
			}
		}
	}

	var session = NewPractice(list);
	session.Invoke();

	os.Exit(0);
}
