package database;

import entities.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PersonStorage {

  private List<Person> personList;
  private Map<Integer, Person> personMap;
  private Map<Integer, String[]> dataMap;

  public PersonStorage(String pathToData) {
    fillStorageFromFile(pathToData);
  }

  public List<Person> getPersonList() {
    return Collections.unmodifiableList(personList);
  }

  public Map<Integer, String[]> getDataMap() {
    return Collections.unmodifiableMap(dataMap);
  }

  private void fillStorageFromFile(String path) {
    try {
      personList = new ArrayList<>();
      personMap = new HashMap<>();
      dataMap = new HashMap<>();
      File data = new File(path);
      try (Scanner scanner = new Scanner(data);) {
        int i = 0;
        while (scanner.hasNext()) {
          String[] input = scanner.nextLine().split("\\s+");
          Person person = Person.getPerson(input);
          personList.add(person);
          personMap.put(i, person);
          dataMap.put(i, input);
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
