/* $File: #FILENAME#, $Timestamp: #TIMESTAMP# */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  static void solve(int a, int b) {
    System.out.println(a + b);
    System.out.println(a * b);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int a = in.nextInt();
    int b = in.nextInt();
    solve(a, b);
  }
}
