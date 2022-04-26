/* $File: #FILENAME#, $Timestamp: #TIMESTAMP# */
use std::io;
use std::process;

macro_rules! parse {
    ($it: expr) => {
        $it.next().unwrap().parse().unwrap()
    };
    ($it: expr, $T: ty) => {
        $it.next().unwrap().parse::<$T>().unwrap()
    };
}

fn read_line() -> Result<String, io::Error> {
    let mut line = String::new();
    let _ = match io::stdin().read_line(&mut line) {
        Ok(byte) => byte,
        Err(e) => return Err(e),
    };
    Ok(line)
}

fn tokenize<'a>(buffer: &'a mut String) -> str::SplitWhitespace<'a> {
    buffer.split_whitespace()
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
    let mut line = read_line().expect("n: the number of values");
    let mut toks = tokenize(&mut line);
    let n: usize = parse!(toks);
    let a: Vec<u64> = (0..n).map(|_| parse!(toks)).collect();

    let mut sum: u64 = 0;
    for i in 0..n {
        sum += a[i];
    }

    println!("{}", sum);
}
