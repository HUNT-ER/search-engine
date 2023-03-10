package search;

import database.PersonStorage;
import entities.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllMatchSearchService extends AbstractSearch {

  public AllMatchSearchService(PersonStorage storage) {
    super(storage);
  }

  @Override
  protected Set<Person> getMatchedPersons(List<Integer> indexes) {
    Set<Person> matchedPersons = new HashSet<>();
    for (int i = 0; i < indexes.size(); i++) {
      String[] foundPerson = storage.getStringPerson(indexes.get(i));
      if (isEqualPersons(pattern, foundPerson)) {
        matchedPersons.add(Person.getPerson(foundPerson));
      }
    }
    return matchedPersons;
  }

  private boolean isEqualPersons(String[] query, String[] arrayInStorage) {
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
