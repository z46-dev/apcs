let n = 1000000,
    initTime = 0,
    testTime = 0;

// Initialize the test
initTime = performance.now();
const arr = [];
for (let i = 0; i < n; i ++) {
    arr.push(n);
}
initTime = performance.now() - initTime;

// Run the test
testTime = performance.now();
for (let i = 0; i < n; i ++) {
    arr[i] *= 2;
}
testTime = performance.now() - testTime;

// Print the results
console.log(`Init time: ${initTime.toFixed(2)}ms`);
console.log(`Test time: ${testTime.toFixed(2)}ms`);