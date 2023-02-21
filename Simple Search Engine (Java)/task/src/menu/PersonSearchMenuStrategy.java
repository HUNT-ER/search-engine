package menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import entities.Person;
import search.Session;

public class PersonSearchMenuStrategy implements MenuStrategy {

  private List<Person> personList;

  @Override
  public void showMenu(Session session) {
    insertPersonsFromFile(session.getPathToData());
    Scanner scanner = new Scanner(System.in);
    int choice;
    menu:
    do {
      printMenu();
      choice = Integer.parseInt(scanner.nextLine().trim());
      switch (choice) {
        case 1:
          find();
          break;
        case 2:
          printAllPeople();
          break;
        case 0:
          System.out.println("\nBye!");
          break menu;
        default:
          System.out.println("\nIncorrect option! Try again.\n");
      }
    } while (true);
  }

  private void insertPeoples() {
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

  private void insertPersonsFromFile(String path) {
    try {
      personList = new ArrayList<>();
      File data = new File(path);
      try (Scanner scanner = new Scanner(data);) {
        while (scanner.hasNext()) {
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
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private void find() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nEnter a name or email to search all suitable people.\n> ");
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
      return;
    }

    System.out.println("\nFound people:");
    for (int j = 0; j < matches.size(); j++) {
      System.out.println(matches.get(j));
    }
  }

  private void printMenu() {
    System.out.println("\n=== Menu ===\n"
        + "1. Find a person\n"
        + "2. Print all people\n"
        + "0. Exit");
  }

  private void printAllPeople() {
    System.out.println("\n=== List of people ===");
    personList.forEach(System.out::println);
    System.out.println();
  }
}
