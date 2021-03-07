/* $File: #FILENAME#, $Timestamp: #TIMESTAMP# */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

public class AtCoder {
  private static FastScanner scanner;
  private static AtCoder solver;

  public static void main(String[] args) {
    solver = new AtCoder();
    scanner = solver.new FastScanner();
    try {
      solver.solve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void solve() {
    System.out.println("Input int");
    int a = scanner.nextInt();
    System.out.println(a);
  }

  private static int getLowerBound(int[] target, int key) {
    int l = 0;
    int r = target.length - 1;
    int m = (l + r) / 2;
    while (true) {
      if (target[m] == key || target[m] > key) {
        r = m - 1;
        if (r < l) return m;
      } else {
        l = m + 1;
        if (r < l) return m < target.length - 1 ? m + 1 : -1;
      }
      m = (l + r) / 2;
    }
  }

  private static int getUpperBound(int[] target, int key) {
    int l = 0;
    int r = target.length - 1;
    int m = (l + r) / 2;
    while (true) {
      if (target[m] == key || target[m] < key) {
        l = m + 1;
        if (r < l) return m < target.length - 1 ? m + 1 : -1;
      } else {
        r = m - 1;
        if (r < l) return m;
      }
      m = (l + r) / 2;
    }
  }

  private class FastScanner {
    private InputStream in;
    private byte[] buffer;
    private int ptr;
    private int len;

    public FastScanner() {
      this.in = System.in;
      this.buffer = new byte[1024];
      this.ptr = 0;
      this.len = 0;
    }

    public FastScanner(String input) {
      try {
	this.in = new FileInputStream(new File(input));
	this.buffer = new byte[1024];
	this.ptr = 0;
	this.len = 0;
      } catch (FileNotFoundException e) {
	e.printStackTrace();
      }
    }

    private boolean hasNextByte() {
      if (ptr < len) {
        return true;
      } else {
        ptr = 0;
        try {
          len = in.read(buffer);
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (len <= 0) return false;
      }
      return true;
    }

    private int readByte() {
      if (hasNextByte()) return buffer[ptr++];
      else return -1;
    }

    private boolean isPrintableChar(int c) {
      return 33 <= c && c <= 126;
    }

    public boolean hasNext() {
      while (hasNextByte() && !this.isPrintableChar(buffer[ptr])) ptr++;
      return hasNextByte();
    }

    public String next() {
      if (!hasNext()) throw new NoSuchElementException();
      StringBuilder sb = new StringBuilder();
      int b = readByte();
      while (this.isPrintableChar(b)) {
        sb.appendCodePoint(b);
        b = readByte();
      }
      return sb.toString();
    }

    public long nextLong() {
      if (!hasNext()) throw new NoSuchElementException();
      long n = 0;
      boolean minus = false;
      int b = readByte();
      if (b == '-') {
        minus = true;
        b = readByte();
      }
      if (b < '0' || '9' < b) throw new NumberFormatException();
      while (true) {
        if ('0' <= b && b <= '9') {
          n *= 10;
          n += b - '0';
        } else if (b == -1 || !this.isPrintableChar(b)) {
          return minus ? -n : n;
        } else {
          throw new NumberFormatException();
        }
        b = readByte();
      }
    }
    public int nextInt() {
      long nl = nextLong();
      if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
      return (int) nl;
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
