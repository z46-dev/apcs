package main

import (
	"fmt"
	"time"
)

func parseTime(time int64) string {
	return fmt.Sprintf("%.2f", (float64(time)/1000000)) + "ms"
}

func main() {
	var n int = 10000000
	var initTime, testTime int64 = 0, 0

	// Initialize the test
	initTime = time.Now().UnixNano()
	var arr []int = make([]int, n)
	for i := 0; i < n; i++ {
		arr[i] = i
	}
	initTime = time.Now().UnixNano() - initTime

	// Run the test
	testTime = time.Now().UnixNano()
	for i := 0; i < n; i++ {
		arr[i] *= 2
	}
	testTime = time.Now().UnixNano() - testTime

	// Print the results
	fmt.Println("Init time: ", parseTime(initTime))
	fmt.Println("Test time: ", parseTime(testTime))
}
