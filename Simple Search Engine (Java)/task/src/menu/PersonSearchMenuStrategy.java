package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import search.Person;
import search.Session;

public class PersonSearchMenuStrategy implements MenuStrategy {
  private List<Person> personList;

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
    personList = new ArrayList<>();
    int numberOfPersons = Integer.parseInt(scanner.nextLine().trim());

    System.out.println("\nEnter all people:");
    for (int i = 0; i < numberOfPersons; i++) {
      System.out.print("> ");
      String[] input = scanner.nextLine().split("\\s+");
      Person person = null;
      switch (input.length) {
        case 1:
          person = new Person(input[0]);
          break;
        case 2:
          person = new Person(input[0], input[1]);
          break;
        case 3:
          person = new Person(input[0], input[1], input[2]);
          break;
      }
      personList.add(person);
    }
  }

  private void find() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nEnter the number of search queries:\n> ");
    try {
      int countOfScans = Integer.parseInt(scanner.nextLine().trim());
      for (int i = 0; i < countOfScans; i++) {
        System.out.print("\nEnter data to search people:\n> ");
        String pattern = scanner.nextLine().toLowerCase();
        personList.stream()
            .filter(
                person -> person.getFirstName().toLowerCase().contains(pattern) ||
                person.getLastName().toLowerCase().contains(pattern) ||
                person.getEmail().toLowerCase().contains(pattern)
                ).collect(Collectors.toList());

        List<Person> matches = personList.stream().filter(
                person -> person.getFirstName().toLowerCase().contains(pattern) ||
                    person.getLastName().toLowerCase().contains(pattern) ||
                    person.getEmail().toLowerCase().contains(pattern)).collect(Collectors.toList());

        if (matches.size() == 0 || "".equals(pattern)) {
          System.out.println("No matching people found.");
          continue;
        }

        System.out.println("\nFound people:");
        for (int j = 0; j < matches.size(); j++) {
          System.out.println(matches.get(j));
        }
      }
    } catch (InputMismatchException e) {
      e.printStackTrace();
    }
  }

}
