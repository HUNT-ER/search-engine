package search;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    BufferedReader lineInput = new BufferedReader(new InputStreamReader(System.in));

    try {
      System.out.print("> ");
      String inputString = lineInput.readLine();
      System.out.print("> ");
      String searchString = lineInput.readLine();

      String[] strings = inputString.split(" ");
      for (int i = 0; i < strings.length; i++) {
        if (searchString.equals(strings[i])) {
          System.out.println(i+1);
          break;
        }
        if (i == strings.length-1) {
          System.out.println("Not found");
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  }
}
