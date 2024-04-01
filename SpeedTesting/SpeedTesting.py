import time

n = 1000000
initTime = 0
testTime = 0

def parseTime(time):
    return str(round(time * 1000, 2)) + "ms"

# Initialize the test
initTime = time.time()
data = [i for i in range(n)]
initTime = time.time() - initTime

# Run the test
testTime = time.time()
for i in range(n):
    data[i] = data[i] * 2
testTime = time.time() - testTime

# Print the results
print("Init time:", parseTime(initTime))
print("Test time:", parseTime(testTime))