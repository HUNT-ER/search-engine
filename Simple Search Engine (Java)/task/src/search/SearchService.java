package search;

import database.PersonStorage;
import entities.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchService {

  public void find(PersonStorage storage) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nEnter a name or email to search all suitable people.\n> ");
    String pattern = scanner.nextLine();

    Map<String, ArrayList<Integer>> invertedMap = invertIndex(storage.getDataMap());

    if (!invertedMap.containsKey(pattern)) {
      System.out.println("No matching people found.");
      return;
    }

    List<Integer> indexes = invertedMap.get(pattern);

    System.out.println("\n" + indexes.size() + " persons found:");
    for (int i = 0; i < indexes.size(); i++) {
      String[] foundPerson = storage.getDataMap().get(indexes.get(i));
      switch (foundPerson.length) {
        case 1:
          System.out.println(new Person(foundPerson[0]));
          break;
        case 2:
          System.out.println(new Person(foundPerson[0], foundPerson[1]));
          break;
        case 3:
          System.out.println(new Person(foundPerson[0], foundPerson[1], foundPerson[2]));
          break;
      }
    }
  }

  private Map<String, ArrayList<Integer>> invertIndex(Map<Integer, String[]> storage) {
    Map<String, ArrayList<Integer>> invertedMap = new HashMap<>();

    for (Map.Entry<Integer, String[]> person : storage.entrySet()) {
      String[] values = person.getValue();
      for (int i = 0; i < values.length; i++) {
        if (invertedMap.containsKey(values[i])) {
          invertedMap.compute(values[i], (str, intArr) -> {
            if (str == null) {
              intArr = new ArrayList<>();
              intArr.add(person.getKey());
            } else {
              intArr.add(person.getKey());
            }
            return intArr;
          });
        } else {
          ArrayList<Integer> arr = new ArrayList<>();
          arr.add(person.getKey());
          invertedMap.put(values[i], arr);
        }
      }
    }
    return invertedMap;
  }

}
