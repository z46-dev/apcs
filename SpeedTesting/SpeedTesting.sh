echo Speed Testing

# C
echo
echo Testing C
gcc ./SpeedTesting.c -o OUTPUTBINARY
./OUTPUTBINARY
rm OUTPUTBINARY

# Go
echo
echo Testing Go
go run ./SpeedTesting.go

# Java
echo
echo Testing Java
java ./SpeedTesting.java

# NodeJS
echo
echo Testing Node.js
node ./SpeedTesting.js

# Bun
echo
echo Testing Bun
bun run ./SpeedTesting.js

# Python3
echo
echo Testing Python3
python3 ./SpeedTesting.py

# Rust
echo
echo Testing Rust
rustc ./SpeedTesting.rs -o OUTPUTBINARY
./OUTPUTBINARY
rm OUTPUTBINARY