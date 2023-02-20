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
      find();
    } catch (IOException e) {
      e.printStackTrace();
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
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nEnter the number of search queries:\n> ");
    try {
      int countOfScans = scanner.nextInt();
      for (int i = 0; i < countOfScans; i++) {
        System.out.print("\nEnter data to search people:\n> ");
        String pattern = input.readLine().toLowerCase();

        List<String> matches = Arrays.stream(peopleList).filter(x -> x.toLowerCase().contains(pattern)).collect(Collectors.toList());

        if (matches.size() == 0 || "".equals(pattern)) {
          System.out.println("No matching people found.");
          continue;
        }

        System.out.println("\nFound people:");
        for (int j = 0; j < matches.size(); j++) {
          System.out.println(matches.get(j));
        }
      }
    } catch (InputMismatchException | IOException e) {
      e.printStackTrace();
    }
  }

}
