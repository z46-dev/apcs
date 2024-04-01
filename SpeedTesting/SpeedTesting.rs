fn get_time() -> i128 {
    std::time::Instant::now().elapsed().as_nanos() as i128
}

// Format a time in nanos to millis with 2 decimal places
fn parse_time(time: i128) -> f64 {
    let k = (time as f64) / 1_000_000.0;
    (k * 100_000.0).round() / 10.0
}

fn main() {
    const N: u32 = 1_000_000;

    // Initialize the test
    let init_time = get_time();
    let mut v: Vec<u32> = Vec::new();
    for i in 0..N {
        v.push(i);
    }
    let init_time = get_time() - init_time;

    // Run the test
    let test_time = get_time();
    for element in v.iter_mut() {
        *element *= 2;
    }
    let test_time = get_time() - test_time;

    // Print the results
    println!("Init time: {}ms", parse_time(init_time));
    println!("Test time: {}ms", parse_time(test_time));
}
