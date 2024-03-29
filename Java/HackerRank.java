/* $File: #FILENAME#, $Timestamp: #TIMESTAMP# */
import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;


public class #FILENAME# {
  static Scanner scan;

  static #FILENAME# solver;

  public static void main(String[] args) {
    try {
      scan   = new Scanner(new FileInputStream(new File(args[0])));
      solver = new #FILENAME#(scan);
      solver.solve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  #FILENAME#(Scanner scan) {
    // Placeholder.
  }

  void solve() {
    // Placeholder.
  }
}
