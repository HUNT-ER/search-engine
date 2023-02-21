package database;

import entities.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PersonStorage {
  private List<Person> personList;
  private Map<Integer, Person> personMap;
  private Map<Integer, String[]> personMapString;

  public PersonStorage(String pathToData) {
    fillStorageFromFile(pathToData);
  }

  public List<Person> getPersonList() {
    return Collections.unmodifiableList(personList);
  }

  private void fillStorageFromFile(String path) {
    try {
      personList = new ArrayList<>();
      personMap = new HashMap<>();
      personMapString = new HashMap<>();
      File data = new File(path);
      try (Scanner scanner = new Scanner(data);) {
        int i = 0;
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
          personMap.put(i, person);
          personMapString.put(i, input);
          i++;
        }
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public void printAllPersons() {
    System.out.println("\n=== List of people ===");
    personList.forEach(System.out::println);
  }
}
