package search;

import database.PersonStorage;
import entities.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import session.UserInput;

public abstract class AbstractSearch {

  protected String[] pattern;
  protected PersonStorage storage;

  public AbstractSearch(PersonStorage storage) {
    this.storage = storage;
  }
  public void find() {
    System.out.print("\nEnter a name or email to search all suitable people.\n> ");
    pattern = UserInput.getStringInput().split("\\s+");

    Map<String, ArrayList<Integer>> invertedMap = invertIndex(storage.getDataMap());
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < pattern.length; i++) {
      if (invertedMap.containsKey(pattern[i])) {
        indexes.addAll(invertedMap.get(pattern[i]));
      }
    }
    Set<Person> matchedPersons = getMatchedPersons(indexes);

    if (matchedPersons.size() == 0) {
      System.out.println("No matching people found.");
      return;
    }
    System.out.println(matchedPersons.size() + " persons found:");
    matchedPersons.forEach(System.out::println);
  }

  ;

  protected Map<String, ArrayList<Integer>> invertIndex(Map<Integer, String[]> storage) {
    Map<String, ArrayList<Integer>> invertedMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

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

  protected abstract Set<Person> getMatchedPersons(List<Integer> indexes);
}
