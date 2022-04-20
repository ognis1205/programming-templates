/* $File: #FILENAME#, $Timestamp: #TIMESTAMP# */
use std::io;
use std::process;

macro_rules! wsv {
    ($it: expr) => {
        $it.next().unwrap().parse().unwrap()
    };
    ($it: expr, $T: ty) => {
        $it.next().unwrap().parse::<$T>().unwrap()
    };
}

fn read_line(buffer: &mut String) -> Result<(), io::Error> {
    let _ = match io::stdin().read_line(buffer) {
        Ok(byte) => byte,
        Err(e) => return Err(e),
    };
    Ok(())
}

/* Main Function.
 * Stuff you should look for:
 *  - int overflow, array bounds
 *  - special cases (n=1?)
 *  - do smth instead of nothing and stay organized
 *  - WRITE STUFF DOWN
 *  - DON'T GET STUCK ON ONE APPROACH
 */
fn main() {
    let mut buf = String::new();
    match read_line(&mut buf) {
        Ok(_) => (),
        Err(e) => {
            eprintln!("Error: {}", e);
            process::exit(1);
        }
    };

    let mut it = buf.split_whitespace();
    let n: usize = wsv!(it);
    let a: Vec<u64> = (0..n).map(|_| wsv!(it)).collect();

    let mut sum: u64 = 0;
    for i in 0..n {
        sum += a[i];
    }

    println!("{}", sum);
}
