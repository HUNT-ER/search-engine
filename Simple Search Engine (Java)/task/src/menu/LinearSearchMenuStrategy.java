package menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import search.Session;

public class LinearSearchMenuStrategy implements MenuStrategy {
  private String[] peopleList;

  @Override
  public void showMenu(Session session) {
    try {
      insertPeoples();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (true) {
      find();
    }
  }

  private void insertPeoples() throws IOException {
    System.out.print("Enter the number of people:\n> ");
    Scanner scanner = new Scanner(System.in);
    String[] peoples = null;
    try {
      int numberOfStrings = scanner.nextInt();
      peoples = new String[numberOfStrings];

      System.out.println("\nEnter all people:");
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      for (int i = 0; i < peoples.length; i++) {
        System.out.print("> ");
        peoples[i] = input.readLine();
      }
      this.peopleList = peoples;
    } catch (InputMismatchException e) {
      e.printStackTrace();
    }
  }

  private void find() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nEnter the number of search queries:\n> ");
    try {
      int maxResultCount = scanner.nextInt();
      System.out.print("\nEnter data to search people:\n> ");
      String pattern = scanner.next().toLowerCase();

      List<String> matches = Arrays.stream(peopleList).filter(x -> x.toLowerCase().contains(pattern)).collect(Collectors.toList());

      if (matches.size() == 0) {
        System.out.println("No matching people found.");
        return;
      }

      for (int i = 0; i < matches.size() & i <= maxResultCount; i++) {
        System.out.println(matches.get(i));
      }
    } catch (InputMismatchException e) {
      e.printStackTrace();
    }
  }

}
