package search;

import database.PersonStorage;
import entities.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import session.UserInput;

public class AllOptionSearchService extends AbstractSearch {

  public void find(PersonStorage storage) {
    System.out.print("\nEnter a name or email to search all suitable people.\n> ");
    String[] pattern = UserInput.getStringInput().split("\\s+");

    Map<String, ArrayList<Integer>> invertedMap = super.invertIndex(storage.getDataMap());
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < pattern.length; i++) {
      if (invertedMap.containsKey(pattern[i])) {
        indexes.addAll(invertedMap.get(pattern[i]));
      }
    }
    Set<Person> matchedPersons = new HashSet<>();
    for (int i = 0; i < indexes.size(); i++) {
      String[] foundPerson = storage.getDataMap().get(indexes.get(i));
      if (isEqualArrays(pattern, foundPerson)) {
        matchedPersons.add(Person.getPerson(foundPerson));
      }
    }
    if (matchedPersons.size() == 0) {
      System.out.println("No matching people found.");
      return;
    }
    System.out.println(matchedPersons.size() + " persons found:");
    matchedPersons.forEach(System.out::println);
  }

  private boolean isEqualArrays(String[] query, String[] arrayInStorage) {
    List<String> person = new ArrayList<>(Arrays.asList(arrayInStorage));
    boolean isEqual = true;
    for (int i = 0; i < query.length; i++) {
      if (!person.contains(query[i])) {
        isEqual = false;
      }
        break;
      }
    return isEqual;
  }

}
