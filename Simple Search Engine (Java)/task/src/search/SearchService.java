package search;

import database.PersonStorage;
import entities.Person;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchService {

  public void find(PersonStorage storage) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nEnter a name or email to search all suitable people.\n> ");
    String pattern = scanner.nextLine().toLowerCase();

    List<Person> matches = storage.getPersonList().stream().filter(
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
}
