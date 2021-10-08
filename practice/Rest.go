package main
import (
	"bytes"
	"encoding/json"
	"io/ioutil"
	"os"
	"fmt"
	"net/http"
	"strings"
)

func StrToDictionary(s []byte) map[string]interface{} {
	var obj map[string]interface{};
	json.Unmarshal(s, &obj);
	return obj;
}

func StrDictionaryToJsonString (a map[string]string) string {
	var result = "{";
	for key, value := range a {
		result += fmt.Sprintf("\"%s\":\"%s\"", key, value);
	}
	result += "}";
	return result;
}

func InvokeGet(endpoint string, headers map[string]string) map[string]interface{} {
	var client = http.Client{};
	req, err := http.NewRequest("GET", endpoint, nil);
	for key, value := range headers {
		req.Header.Add(key, value);
	}
	rsp, err := client.Do(req);
	if err == nil {
		rspData, _ := ioutil.ReadAll(rsp.Body);
		return StrToDictionary(rspData);
	}
	return nil;
}

func InvokePost(endpoint string, jsonBody map[string]string,  headers map[string]string) map[string]interface{} {
	body := StrDictionaryToJsonString(jsonBody)
	var client = http.Client{};
	req, err := http.NewRequest("POST", endpoint, bytes.NewReader([]byte(body)));
	for key, value := range headers {
		req.Header.Add(key, value);
	}
	rsp, err := client.Do(req);
	if err == nil {
		rspData, _ := ioutil.ReadAll(rsp.Body);
		return StrToDictionary(rspData);
	}
	return nil;
}

func main() {
	var methodType = "GET";
	var endpoint   = "";
	var body	   = map[string]string {};
	var headers	   = map[string]string {};

	for i := 0; i < len(os.Args); i++ {
		if os.Args[i] == "-m" {
			methodType = strings.ToUpper(os.Args[i + 1]);
		} else if os.Args[i] == "--endpoint" {
			endpoint = os.Args[i + 1];
		} else if os.Args[i] == "-h" {
			var pairs = strings.Split(os.Args[i + 1], ",");
			for _, pair := range pairs {
				var comps = strings.Split(pair, ":");
				if len(comps) > 1 {
					headers[comps[0]] = comps[1];
				}
			}
		} else if os.Args[i] == "-d" {
			var pairs = strings.Split(os.Args[i + 1], ",");
			for _, pair := range pairs {
				var comps = strings.Split(pair, ":");
				if len(comps) > 1 {
					body[comps[0]] = comps[1];
				}
			}
		}
	}

	var rsp map[string]interface{};
	switch (methodType) {
		case "GET":
			rsp = InvokeGet(endpoint, headers);
			break;
		case "POST":
			rsp = InvokePost(endpoint, body, headers);
			break;
		default:
			fmt.Printf("Method (%s) not valid...\n", methodType);
			os.Exit(-1);
			break;
	}
	fmt.Printf("Response: %v\n", rsp);
	os.Exit(0);
}
