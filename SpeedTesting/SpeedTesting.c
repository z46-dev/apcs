#include <stdio.h>
#include <stdlib.h>
#include <time.h>

char* parseTime(long time) {
    char* result = (char*)malloc(10 * sizeof(char));
    sprintf(result, "%.2fms", (double)time / CLOCKS_PER_SEC * 1000);
    return result;
}

int main() {
    int n = 10000000;
    long initTime = 0;
    long testTime = 0;

    // Initialize the test
    initTime = clock();
    int* arr = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        arr[i] = i;
    }
    initTime = clock() - initTime;

    // Run the test
    testTime = clock();
    for (int i = 0; i < n; i++) {
        arr[i] *= 2;
    }
    testTime = clock() - testTime;

    // Print the results
    printf("Init time: %s\n", parseTime(initTime));
    printf("Test time: %s\n", parseTime(testTime));

    return 0;
}